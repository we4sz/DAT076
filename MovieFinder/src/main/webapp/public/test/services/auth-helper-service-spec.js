describe('Service: movieFinder.services.authHelper', function () {
    'use strict';

    var AUTH_LOGIN_PATH = '/TestLogin';
    var AUTH_EVENTS, authHelperService, userMock, location;

    beforeEach(function () {
        userMock = {
            'hasRole': function () {
            },
            'isLoggedIn': function () {
            }
        };
        AUTH_EVENTS = {
            forbidden: 'forbidden',
            loginRequired: 'loginRequired'
        };

        // Set up the module that hold our service
        module('movieFinder.services', function ($provide) {
            // Set up all dependencies the service requires
            $provide.value('AUTH_EVENTS', AUTH_EVENTS);
            $provide.value('AUTH_LOGIN_PATH', AUTH_LOGIN_PATH);
            $provide.value('user', userMock);
        });

        // Get an instance of the service itself
        inject(function ($location, _authHelper_) {
            authHelperService = _authHelper_;
            location = $location;
        });
    });

    describe('#saveAttemptRoute', function () {
        it('should not set the attempted route to the AUTH_LOGIN_PATH', function () {
            // As to avoid redirecting to login page after login...
            location.path(AUTH_LOGIN_PATH);
            authHelperService.saveAttemptRoute();
            authHelperService.redirectToAttemptRoute();
            expect(location.path()).not.toBe(AUTH_LOGIN_PATH);
        });
    });

    describe('#redirectToLoginPage', function () {

        it('should redirect to the defined login page', function () {
            spyOn(location, 'path').andCallThrough();
            authHelperService.redirectToLoginPage();
            expect(location.path).toHaveBeenCalledWith(AUTH_LOGIN_PATH);
        });

        it('should set the reason for having to log in', function () {
            authHelperService.redirectToLoginPage('TEST_REASON');
            expect(authHelperService.getLoginReason()).toBe('TEST_REASON');
        });

        it('should save the current path when redirecting to the login page', function () {
            location.path('/someRoute');
            authHelperService.redirectToLoginPage();
            expect(location.path()).toBe(AUTH_LOGIN_PATH);

            authHelperService.redirectToAttemptRoute();
            expect(location.path()).toBe('/someRoute');
        });
    });


    describe('#restrictRoute', function () {
        it('should return true for routes the user\'s role has access to', function () {
            spyOn(userMock, 'hasRole').andReturn(true);
            var restrictResult = authHelperService.restrictRoute(['ADMIN']);

            expect(userMock.hasRole).toHaveBeenCalledWith(['ADMIN']);
            expect(restrictResult).toBe(true);
        });

        it('should return a rejected promise for routes the user\'s role does not have access to', inject(function ($rootScope) {
            spyOn(userMock, 'hasRole').andReturn(false);
            var restrictResult = authHelperService.restrictRoute();

            expect(restrictResult.then).toBeDefined();

            var wasRejected = false;
            restrictResult.then(null, function () {
                wasRejected = true;
            });
            // $digest to evaluate the promise
            $rootScope.$digest();

            expect(wasRejected).toBe(true);
        }));


        it('should reject the promise with loginRequired if user is not logged in', inject(function ($rootScope) {
            spyOn(userMock, 'hasRole').andReturn(false);
            spyOn(userMock, 'isLoggedIn').andReturn(false);

            var rejection = null;
            authHelperService.restrictRoute().then(null, function (rej) {
                rejection = rej;
            });

            // $digest to evaluate the promise
            $rootScope.$digest();

            expect(rejection).toBe(AUTH_EVENTS.loginRequired);
        }));

        it('should reject the promise with forbidden if user is logged in but role is not allowed', inject(function ($rootScope) {
            spyOn(userMock, 'hasRole').andReturn(false);
            spyOn(userMock, 'isLoggedIn').andReturn(true);

            var rejection = null;
            authHelperService.restrictRoute().then(null, function (rej) {
                rejection = rej;
            });

            // $digest to evaluate the promise
            $rootScope.$digest();

            expect(rejection).toBe(AUTH_EVENTS.forbidden);
        }));
    });
});


describe('Service: movieFinder.services.authInterceptor', function () {
    'use strict';

    var AUTH_EVENTS, authInterceptorService, rootScope;

    beforeEach(function () {
        AUTH_EVENTS = {
            forbidden: 'forbidden',
            loginRequired: 'loginRequired'
        };

        // Set up the module that hold our service
        module('movieFinder.services', function ($provide) {
            // Set up all dependencies the service requires
            $provide.value('AUTH_EVENTS', AUTH_EVENTS);
        });

        // Create a spy on the root scope
        inject(function ($injector) {
            rootScope = $injector.get('$rootScope');
            spyOn(rootScope, '$broadcast');
        });

        // Get an instance of the service itself
        inject(function (_authInterceptor_) {
            authInterceptorService = _authInterceptor_;
        });
    });

    it('should broadcast a "loginRequired" event for HTTP 401', function () {
        var rejection = {
            status: 401
        };
        authInterceptorService.responseError(rejection);
        expect(rootScope.$broadcast).toHaveBeenCalled();
        expect(rootScope.$broadcast.mostRecentCall.args[0]).toBe(AUTH_EVENTS.loginRequired);
    });

    it('should broadcast a "forbidden" event for HTTP 403', function () {
        var rejection = {
            status: 403
        };
        authInterceptorService.responseError(rejection);
        expect(rootScope.$broadcast).toHaveBeenCalled();
        expect(rootScope.$broadcast.mostRecentCall.args[0]).toBe(AUTH_EVENTS.forbidden);
    });

});


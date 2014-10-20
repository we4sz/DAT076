describe('Controller: movieFinder.controllers.AppCtrl', function () {
    'use strict';

    beforeEach(function () {
        module('movieFinder.controllers');

        module(function ($provide) {
            $provide.value('user', {});
            $provide.value('$route', {
                reload: function () {
                }
            });
            $provide.value('AUTH_EVENTS', {});
            $provide.value('authHelper', {});
        });
    });

    it('should not indicate an error on succesful route', inject(function ($controller) {
        var appCtrl = $controller('AppCtrl');
        expect(appCtrl.error.isError).toBe(false);
    }));

    it('should indicate an error on unsuccesful route', inject(function ($controller, $rootScope) {
        var appCtrl = $controller('AppCtrl');
        $rootScope.$emit('$routeChangeError', null, null, 'message');
        expect(appCtrl.error.isError).toBe(true);
        expect(appCtrl.error.errorMessage).toBe('message');
    }));

    it('should cancel any errors when navigating to a new route', inject(function ($controller, $rootScope) {
        var appCtrl = $controller('AppCtrl');
        $rootScope.$emit('$routeChangeError', null, null, 'message');
        expect(appCtrl.error.isError).toBe(true);
        $rootScope.$emit('$routeChangeStart', null, null, 'message');
        expect(appCtrl.error.isError).toBe(false);
    }));

    it('should not indicate loading on loaded route', inject(function ($controller) {
        var appCtrl = $controller('AppCtrl');
        expect(appCtrl.loading.isLoading).toBe(false);
    }));

    it('should indicate loading when a route is loading', inject(function ($controller, $rootScope, $timeout) {
        var appCtrl = $controller('AppCtrl');
        var next = {
            resolve: true
        };
        // Simulate a delayed load
        $rootScope.$emit('$routeChangeStart', next);
        $timeout.flush(2000);

        expect(appCtrl.loading.isLoading).toBe(true);

        $rootScope.$emit('$routeChangeSuccess', null, next);

        expect(appCtrl.loading.isLoading).toBe(false);
    }));

    it('should not indicate loading for quickly loaded routes', inject(function ($controller, $rootScope, $timeout) {
        var appCtrl = $controller('AppCtrl');
        var next = {
            resolve: true
        };
        // Simulate a very short delayed load
        $rootScope.$emit('$routeChangeStart', next);
        $timeout.flush(25);
        expect(appCtrl.loading.isLoading).toBe(false);

        $rootScope.$emit('$routeChangeSuccess', null, next);
        expect(appCtrl.loading.isLoading).toBe(false);
    }));

});

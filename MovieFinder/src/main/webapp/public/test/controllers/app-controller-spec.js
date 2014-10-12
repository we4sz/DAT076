describe('Controller: movieFinder.controllers.AppCtrl', function() {
    'use strict';

    beforeEach(function() {
        module('movieFinder.controllers');
        
        module(function ($provide) {
            $provide.value('user', {});
            $provide.value('$route', {
                reload: function(){}
            });
            $provide.value('AUTH_EVENTS', {});
            $provide.value('authHelper', {});
      });
    });

    it('should not indicate an error on succesful route', inject(function($controller) {
        var appCtrl = $controller('AppCtrl');
        expect(appCtrl.error.isError).toBe(false);
    }));

    it('should indicate an error on unsuccesful route', inject(function($controller, $rootScope) {
        var appCtrl = $controller('AppCtrl');
        $rootScope.$emit('$routeChangeError', null, null, 'message');
        expect(appCtrl.error.isError).toBe(true);
        expect(appCtrl.error.errorMessage).toBe('message');
    }));

    it('should cancel any errors when navigating to a new route', inject(function($controller, $rootScope) {
        var appCtrl = $controller('AppCtrl');
        $rootScope.$emit('$routeChangeError', null, null, 'message');
        expect(appCtrl.error.isError).toBe(true);
        $rootScope.$emit('$routeChangeStart', null, null, 'message');
        expect(appCtrl.error.isError).toBe(false);
    }));

});

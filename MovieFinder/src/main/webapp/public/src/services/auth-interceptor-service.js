//
// auth-interceptor-service.js
// An http interceptor listening for requests failing with 401 (Unathorized) 
// and updates the local user state if they do.
//

(function() {
    'use strict';

    angular.module('movieFinder.services')
        .factory('authInterceptor', function($q, $rootScope) {
            return {
                'responseError': function(rejection) {
                    if(rejection.status === 401) {
                        // Unathorized, brodcast an loginRequired event
                        $rootScope.$broadcast('auth-loginRequired', rejection);
                    }
                    return $q.reject(rejection);
                }
            };
        });
})();
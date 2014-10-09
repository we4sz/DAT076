//
// auth-interceptor-service.js
// An http interceptor listening for requests failing with
// status 401 (Unathorized) or 403 (Forbidden). If such a
// request is found, an event is emitted.
//
// This is used so that the app can prompt the user to 
// (re-)loggin if requests are being denied.
//

(function() {
    'use strict';

    angular.module('movieFinder.services')
        .factory('authInterceptor', function($q, $rootScope, AUTH_EVENTS) {
            return {
                'responseError': function(rejection) {
                    if(rejection.status === 401) {
                        // Unathorized, brodcast a loginRequired event
                        $rootScope.$broadcast(AUTH_EVENTS.loginRequired, rejection);
                    } else if(rejection.status === 403) {
                        // Forbidden (role not high enough)
                        $rootScope.$broadcast(AUTH_EVENTS.forbidden, rejection);
                    }
                    return $q.reject(rejection);
                }
            };
        });
})();
//
// auth-helper-service.js
// A service for providing helper methods for authentication,
// such as a resolve to prevent users from reaching routes they
// are not authenticated to see.
//

(function() {
    'use strict';

    angular.module('movieFinder.services')
        .factory('authHelper', function($q, $rootScope, AUTH_EVENTS, user) {
            return {
                'restrictRoute': function(allowedRoles) {
                    if(!user.hasRole(allowedRoles)) {
                        if(user.isLoggedIn()) {
                            return $q.reject(AUTH_EVENTS.forbidden);
                        } else {
                            return $q.reject(AUTH_EVENTS.loginRequired);
                        }
                    } else {
                        return true;
                    }
                }
            };
        });
})();
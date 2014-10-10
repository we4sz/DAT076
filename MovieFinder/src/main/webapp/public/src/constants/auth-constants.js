//
// auth-constants.js
// Constants that has to do with auth.
//

(function () {
    'use strict';

    angular.module('movieFinder.constants')
            .constant('AUTH_EVENTS', {
                loginRequired: 'auth-loginRequired',
                forbidden: 'auth-forbidden',
                loginSuccessful: 'auth-loginSuccessful',
                logoutSuccessful: 'auth-logoutSuccessful'
            });
})();
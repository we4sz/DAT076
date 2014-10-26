//
// auth-constants.js
// Constants that has to do with authentication.
//

(function () {
    'use strict';

    angular.module('movieFinder.constants')
            .constant('AUTH_EVENTS', {
                loginRequired: 'auth-loginRequired',
                forbidden: 'auth-forbidden',
                loginSuccessful: 'auth-loginSuccessful',
                logoutSuccessful: 'auth-logoutSuccessful'
            })
            .constant('AUTH_LOGIN_PATH', '/login');
})();
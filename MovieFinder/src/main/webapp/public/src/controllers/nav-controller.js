//
// nav-controller.js
// Contains the controller for the nav-bar.
//

(function() {
    'use strict';

    angular.module('movieFinder.controllers')
        .controller('NavCtrl', function($rootScope, $location, authHelper, AUTH_LOGIN_PATH, user) {
            var _this = this;

            // Flag used to hide the Sign In button when already
            // on the login page.
            this.isLoginPage = ($location.url() === AUTH_LOGIN_PATH);

            this.logout = function() {
                user.logout();
            };

            this.login = function() {
                authHelper.redirectToLoginPage();
            };

            $rootScope.$on('$locationChangeSuccess', function() {
                _this.isLoginPage = ($location.url() === AUTH_LOGIN_PATH);
            });

        });
})();
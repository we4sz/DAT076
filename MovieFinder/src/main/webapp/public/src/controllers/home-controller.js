//
// home-controller.js
// Contains the controller for the home page.
//

(function() {
    'use strict';

    angular.module('movieFinder.controllers')
        .controller('HomeCtrl', function($http, user) {

            this.logout = function() {
                user.logout();
            };

            this.login = function() {
                user.login('abc', '123');
            };
        });
})();
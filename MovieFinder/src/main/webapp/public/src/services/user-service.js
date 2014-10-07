//
// user-service.js
// A representation of a user.
//

(function() {
    'use strict';

    angular.module('movieFinder.services')
        .factory('user', function($http) {

            var loggedIn = false;
            var role = '';
            var username = '';

            return {
                'logout': function() {
                    return $http.post('api/login/logout').success(function() {
                        loggedIn = false;
                        role = '';
                        username = '';
                    });
                },
                'login': function(name, pass) {
                    return $http
                        .post('api/login/login', {
                            'username': name,
                            'password': pass
                        })
                        .success(function(userData) {
                            loggedIn = true;
                            role = userData.role;
                            username = userData.username;
                        });
                },
                'isLoggedIn': function() {
                    return loggedIn;
                },
                'getUsername': function() {
                    return username;
                },
                'getRole': function() {
                    return role;
                }
            };
        });
})();
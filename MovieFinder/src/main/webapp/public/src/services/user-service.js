//
// user-service.js
// A representation of a user.
//

(function() {
    'use strict';

    angular.module('movieFinder.services')
        .factory('user', function($http, $window) {

            var loggedIn = false;
            var role = '';
            var username = '';

            var setUserData = function setUserData(userData) {
                loggedIn = true;
                role = userData.role;
                username = userData.username;
            };

            if($window.session_user){
                setUserData($window.session_user);
                $window.session_user = null;
            }

            return {
                'logout': function() {
                    return $http.post('api/login/logout').success(function() {
                        loggedIn = false;
                        role = '';
                        username = '';
                    });
                },
                'login': function(name, pass) {
                    return $http.post('api/login/login', {
                            'username': name,
                            'password': pass
                        }).success(setUserData);
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
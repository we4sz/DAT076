//
// user-service.js
// A representation of a user.
//

(function() {
    'use strict';

    angular.module('movieFinder.services')
        .factory('user', function($http, $window, $rootScope, USER_ROLES, AUTH_EVENTS) {

            var _loggedIn = false;
            var _role = null;
            var _username = null;

            var _setUserData = function (userData) {
                _loggedIn = true;
                if(userData.role in USER_ROLES) {
                    _role = USER_ROLES[userData.role];
                }
                _username = userData.username;

                $rootScope.$broadcast(AUTH_EVENTS.loginSuccessful);
            };

            var _clearUserData = function () {
                _loggedIn = false;
                _role = null;
                _username = null;

                $rootScope.$broadcast(AUTH_EVENTS.logoutSuccessful);
            };

            if($window.session_user){
                _setUserData($window.session_user);
                $window.session_user = null;
            } else {
                _clearUserData();
            }

            return {
                'logout': function() {
                    return $http.post('api/login/logout').success(_clearUserData);
                },
                'login': function(name, pass) {
                    return $http.post('api/login/login', {
                            'username': name,
                            'password': pass
                        }).success(_setUserData);
                },
                'isLoggedIn': function() {
                    return _loggedIn;
                },
                'getUsername': function() {
                    return _username;
                },
                'getRole': function() {
                    return _role;
                },
                'hasRole': function(role) {
                    if(angular.isArray(role)) {
                        return role.indexOf(_role) > -1;
                    } else {
                        return _role === role;
                    }
                }
            };
        });
})();
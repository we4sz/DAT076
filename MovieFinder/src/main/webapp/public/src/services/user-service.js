//
// user-service.js
// A representation of a user and various methods related to user, 
// such as login and logout.
//

(function() {
    'use strict';

    angular.module('movieFinder.services')
        .factory('user', function($http, $rootScope, USER_ROLES, AUTH_EVENTS) {
            /**
             * Flag indicating if the user is logged in or not.
             * @type {boolean}
             * @private
             */
            var _loggedIn = false;

            /**
             * The role of the current user, or null if not logged in.
             * @type {string|null}
             * @private
             */
            var _role = null;

            /**
             * The username of the current user, or null if not logged in.
             * @type {string|null}
             * @private
             */
            var _username = null;

            /**
             * Helper method for setting user properties from a user data object.
             * @param userData An object describing a user.
             * @param userData.username The username of the user
             * @param userData.role The role of the user.
             * @private
             */
            var _setUserData = function (userData) {
                _loggedIn = true;
                if(userData.role in USER_ROLES) {
                    _role = USER_ROLES[userData.role];
                }
                _username = userData.username;
            };

            /**
             * Helper method for clearing the user properties of the current user.
             * @private
             */
            var _clearUserData = function () {
                _loggedIn = false;
                _role = null;
                _username = null;
            };

            // This is a slightly hackish way to reduce the number of requests needed
            // when our app is initialized. Instead of having to ping some api for our
            // current user object, we inject it directly in the html and fetch it here.
            (function(){
                var injectedUser = {
                    username: angular.element(document.querySelector('meta[name="_user_username"]')).attr('content'),
                    role: angular.element(document.querySelector('meta[name="_user_role"]')).attr('content')
                };
                if(injectedUser.username && injectedUser.role){
                    _setUserData(injectedUser);
                } else {
                    _clearUserData();
                }
            })();

            return {
                /**
                 * Un-sets the local user model without contacting the server.
                 */
                'unset': function () {
                    _clearUserData();
                },
                /**
                 * Attempts to logout the user by sending a logout request to the server.
                 * @returns {Promise} The promise for the logout request to the server, resolved on successful
                 *                    logout
                 */
                'logout': function() {
                    return $http.post('api/login/logout').success(function(){
                        _clearUserData();
                        $rootScope.$broadcast(AUTH_EVENTS.logoutSuccessful);
                    });
                },
                /**
                 * Attempts to log in the user by sending a login request to the server.
                 * @param username The name of the user.
                 * @param password The password of the user.
                 * @returns {Promise} A promise resolved on successful login.
                 */
                'login': function (username, password) {
                    return $http.post(
                        'api/login/login', {
                            'username': username,
                            'password': password
                        }).success(function (data) {
                            _setUserData(data);
                            $rootScope.$broadcast(AUTH_EVENTS.loginSuccessful);
                        });
                },
                /**
                 * Checks whether the user is logged in or not.
                 * @returns {boolean}
                 */
                'isLoggedIn': function() {
                    return _loggedIn;
                },
                /**
                 * Return the username of the user, or null if not logged in.
                 * @returns {string|null}
                 */
                'getUsername': function() {
                    return _username;
                },
                /**
                 * Returns the user role, or null if user is not logged in.
                 * @returns {string|null}
                 */
                'getRole': function() {
                    return _role;
                },
                /**
                 * Tests the user's role against a role or an array of roles. Returns true if 
                 * the user's role is the same as the role provided, or if an array, true if 
                 * the role is an element of the array.
                 * @param {string|string[]} role
                 * @returns {boolean}
                 */
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
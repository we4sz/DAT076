//
// auth-helper-service.js
// A service for providing helper methods for authentication,
// such as a resolve to prevent users from reaching routes they
// are not authenticated to see.
//

(function() {
    'use strict';

    angular.module('movieFinder.services')
        .factory('authHelper', function($q, $location, AUTH_EVENTS, AUTH_LOGIN_PATH, user) {

            // var for keeping track of a route that forces user to 
            // be logged in. Used so that we can redirect back to 
            // it after a succesful login.
            var _attemptRoute = '/';

            // A reason for the login. Set when the login is force-shown
            // for path that the user is not authenticed to see. Used to
            // alter the login page to display information to the user.
            var _reason;

            return {
                /**
                 * Saves the current path so that it can be redirected
                 * to later.
                 */
                'saveAttemptRoute': function() {
                    if ($location.path().toLowerCase() !== AUTH_LOGIN_PATH.toLowerCase()) {
                        _attemptRoute = $location.path();
                    } else {
                        _attemptRoute = '/';
                    }
                },
                /**
                 * Saves the current path, then redirect to the login page.
                 * Also sets the reason for login to the reason provided.
                 * @param  {String} [reason] The reason why the login is requried.
                 *                           Default to undefined, a user-initiated login.
                 */
                'redirectToLoginPage': function(reason) {
                    this.saveAttemptRoute();
                    _reason = reason;
                    $location.path(AUTH_LOGIN_PATH);
                },
                /**
                 * Returns the reason for why the user must login.
                 * @return {String} The reason for the login.
                 */
                'getLoginReason': function() {
                    return _reason;
                },
                /**
                 * Redirects to the last saved attempt route and unsets the
                 * login reason.
                 */
                'redirectToAttemptRoute': function() {
                    _reason = '';
                    $location.path(_attemptRoute);
                },
                /**
                 * Restricts a route to some role(s). This function
                 * should be specifed as a resolve for the route to
                 * limit.
                 * @param  {String|Array} allowedRoles A string or array of
                 *                                     allowed roles for the route.
                 * @return {Promise|Boolean} Returns a rejected promise if user is not allowed
                 *                        to see the current route. Or true if authenticated.
                 */
                'restrictRoute': function(allowedRoles) {
                    if (!user.hasRole(allowedRoles)) {
                        if (user.isLoggedIn()) {
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
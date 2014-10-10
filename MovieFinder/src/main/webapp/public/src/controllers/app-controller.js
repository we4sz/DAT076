//
// app-controller.js
// Contains the main controller for the app.
//
// The main controller is used to catch route change (error)
// and similar global events.
//

(function () {
    'use strict';

    angular.module('movieFinder.controllers')
            .controller('AppCtrl', function ($rootScope, $route, AUTH_EVENTS, user) {
                var _this = this;

                var onLoginStateChange = function() {
                    $route.reload();
                };

                // An object for holding global error state. Used so 
                // we can show something if a global error occures, like
                // a route faling to load.
                this.error = {
                    isError: false,
                    errorMessage: null
                };

                // The global user service instance
                this.user = user;

                $rootScope.$on('$routeChangeStart', function () {
                    _this.error.isError = false;
                });

                $rootScope.$on(AUTH_EVENTS.loginSuccessful, onLoginStateChange);
                $rootScope.$on(AUTH_EVENTS.logoutSuccessful, onLoginStateChange);

                $rootScope.$on('$routeChangeError', function (event, current, previous, rejection) {
                    if (rejection === AUTH_EVENTS.loginRequired) {
                        // Login is required for this view
                        $rootScope.$broadcast(AUTH_EVENTS.loginRequired);
                    } else if (rejection === AUTH_EVENTS.forbidden) {
                        // The user's role is not allowed on this view
                        $rootScope.$broadcast(AUTH_EVENTS.forbidden);
                    } else {
                        // Unknown error, show the rejection message
                        _this.error.isError = true;

                        if (angular.isString(rejection.message)) {
                            _this.error.errorMessage = rejection.message;
                        } else if (angular.isString(rejection)) {
                            _this.error.errorMessage = rejection;
                        } else {
                            _this.error.errorMessage = 'An unexpected error occurred while loading the page.';
                        }
                    }
                });
            });
})();
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
            .controller('AppCtrl', function ($rootScope, $route, $timeout, AUTH_EVENTS, user, authHelper) {
                var _this = this;

                var onLoginStateChange = function () {
                    $route.reload();
                };

                // Flag used to delay showing of loading animation a few
                // ms to make sure the route actually has something to load

                var stillLoading = false;

                this.loading = {
                    isLoading: false
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

                $rootScope.$on('$routeChangeStart', function (evt, next) {
                    // Only show loading anim if route has something to load
                    if (next && next.resolve) {
                        // Even with something to load, make sure loading takes
                        // atleast 100 ms before showing loading anim, or we
                        // are just wasting our users time
                        stillLoading = true;
                        $timeout(function () {
                            if (stillLoading) {
                                _this.loading.isLoading = true;
                            }
                        }, 100);
                    }
                    _this.error.isError = false;
                });

                $rootScope.$on('$routeChangeSuccess', function () {
                    stillLoading = false;
                    _this.loading.isLoading = false;
                });

                $rootScope.$on(AUTH_EVENTS.loginSuccessful, onLoginStateChange);
                $rootScope.$on(AUTH_EVENTS.logoutSuccessful, onLoginStateChange);

                $rootScope.$on('$routeChangeError', function (event, current, previous, rejection) {

                    _this.loading.isLoading = false;

                    if (rejection === AUTH_EVENTS.loginRequired) {
                        // Login is required for this view
                        authHelper.redirectToLoginPage(AUTH_EVENTS.loginRequired);
                    } else if (rejection === AUTH_EVENTS.forbidden) {
                        // The user's role is not allowed on this view
                        authHelper.redirectToLoginPage(AUTH_EVENTS.forbidden);
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
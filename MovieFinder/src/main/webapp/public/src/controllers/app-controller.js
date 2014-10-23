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

                // Promise set for the loading animation. Used so that the promise
                // can be canceled when route change finishes.
                var showLoadingAnimationPromise;

                this.loading = {
                    isLoading: false
                };

                // An object for holding global error state. Used so we can show
                // something if a global error occurs, like a route failed to load.
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
                        // at least 100 ms before showing loading anim, or we
                        // are just wasting our users time
                        showLoadingAnimationPromise = $timeout(function () {
                            _this.loading.isLoading = true;
                        }, 100);
                    }
                    _this.error.isError = false;
                });

                $rootScope.$on('$routeChangeSuccess', function () {
                    $timeout.cancel(showLoadingAnimationPromise);
                    _this.loading.isLoading = false;
                });

                // Route change errors indicate an error loading a route due to some promise not
                // being resolved.
                $rootScope.$on('$routeChangeError', function (event, current, previous, rejection) {
                    $timeout.cancel(showLoadingAnimationPromise);
                    _this.loading.isLoading = false;

                    if (rejection === AUTH_EVENTS.loginRequired) {
                        $rootScope.$broadcast(AUTH_EVENTS.loginRequired);
                    } else if (rejection === AUTH_EVENTS.forbidden) {
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

                // Event triggered when a login is required.
                // Unset the local user model and redirect to the login page.
                $rootScope.$on(AUTH_EVENTS.loginRequired, function() {
                    user.unset();
                    authHelper.redirectToLoginPage(AUTH_EVENTS.loginRequired);
                });

                // Event triggered when a user's role is not authorized.
                // Redirect to the login page, but do not unset the user reference
                // as the forbidden means user is logged in, just not the right role.
                $rootScope.$on(AUTH_EVENTS.forbidden, function () {
                    authHelper.redirectToLoginPage(AUTH_EVENTS.forbidden);
                });

                // Event triggered when the user is logged out.
                // Reload current route to make sure user is still authenticated.
                $rootScope.$on(AUTH_EVENTS.logoutSuccessful, function(){
                    $route.reload();
                });
            });
})();
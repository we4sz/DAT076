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
            .controller('AppCtrl', function ($rootScope) {
                var _this = this;

                // An object for holding global error state. Used so 
                // we can show something if a global error occures, like
                // a route faling to load.
                this.error = {
                    isError: false,
                    errorMessage: null
                };

                $rootScope.$on('$routeChangeStart', function () {
                    _this.error.isError = false;
                });

                $rootScope.$on('$routeChangeSuccess', function () {
                    // Might use this for some loading animation
                });

                $rootScope.$on('$routeChangeError', function (event, current, previous, rejection) {
                    _this.error.isError = true;

                    if (angular.isString(rejection.message)) {
                        _this.error.errorMessage = rejection.message;
                    } else if (angular.isString(rejection)) {
                        _this.error.errorMessage = rejection;
                    } else {
                        _this.error.errorMessage = 'An unexpected error occurred while loading the page.';
                    }
                });
            });
})();
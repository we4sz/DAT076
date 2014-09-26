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
                // An object for holding global error state. Used so 
                // we can show something if a global error occures, like
                // a route faling to load.
                this.error = {
                    isError: false,
                    errorMessage: null
                };

                $rootScope.$on('$routeChangeStart', function () {
                    this.error.isError = false;
                }.bind(this));

                $rootScope.$on('$routeChangeSuccess', function () {
                    // Might use this for some loading animation
                }.bind(this));

                $rootScope.$on('$routeChangeError', function (event, current, previous, rejection) {
                    console.error('ROUTE CHANGE ERROR: ', rejection);
                    this.error.isError = true;

                    if (angular.isString(rejection.message)) {
                        this.error.errorMessage = rejection.message;
                    } else if (angular.isString(rejection)) {
                        this.error.errorMessage = rejection;
                    } else {
                        this.error.errorMessage = 'An unexpected error occurred while loading the page.';
                    }
                }.bind(this));
            });
})();
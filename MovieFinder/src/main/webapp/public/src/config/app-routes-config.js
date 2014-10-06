//
// app-routes-config.js
// Contains the route mapping for the app.
//

(function () {
    'use strict';

    angular.module('movieFinder')
            .config(function ($routeProvider) {
                $routeProvider
                        .when('/', {
                            templateUrl: 'partials/home.html'
                        })
                        .otherwise({
                            redirectTo: '/'
                        });
            });
})();
//
// app-routes-config.js
// Contains the route mapping for the app.
//

(function () {
    'use strict';

    angular.module('movieFinder')
            .config(['$routeProvider',
                function ($routeProvider) {
                    $routeProvider
                            .when('/', {
                                templateUrl: 'resources/partials/home.html'
                            })
                            .otherwise({
                                redirectTo: '/'
                            });
                }
            ]);
})();
//
// error-directive.js
//
// A directive for displaying an error message and providing go back/retry options.
//

(function () {
    'use strict';

    angular.module('movieFinder.directives').directive('error',
            ['$route', '$rootScope', '$location',
                function ($route, $rootScope, $location) {
                    return {
                        scope: {
                            'errorText': '@'
                        },
                        restrict: 'E', // restrict E - can only be an element (<error></error>)
                        templateUrl: 'partials/directives/error.html',
                        link: function (scope) {
                            var history = [];

                            // Listen for all successful route changes, so that we can
                            // go back to previous route.
                            $rootScope.$on('$routeChangeSuccess', function () {
                                history.push($location.$$path);
                            });

                            // Function to allow retrying a route
                            scope.retry = function () {
                                $route.reload();
                            };
                            // Function to go back to latest successfully loaded route, or
                            // index if non exist.
                            scope.goBack = function () {
                                if (history.length) {
                                    $location.path(history.splice(-1)[0]);
                                } else {
                                    $location.path('/');
                                }
                            };
                        }
                    };
                }
            ]);

})();
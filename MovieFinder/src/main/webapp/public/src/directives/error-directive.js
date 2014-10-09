//
// error-directive.js
//
// A directive for displaying an error message and providing go back/retry options.
//

(function () {
    'use strict';

    angular.module('movieFinder.directives')
            .directive('error', function ($route, $rootScope, $location, history) {
                return {
                    scope: {
                        'errorText': '@'
                    },
                    restrict: 'E', // restrict E - can only be an element (<error></error>)
                    templateUrl: 'partials/directives/error.html',
                    link: function (scope) {

                        // Function to allow retrying a route
                        scope.retry = function () {
                            $route.reload();
                        };
                        // Function to go back to latest successfully loaded route, or
                        // index if non exist.
                        scope.goBack = history.goBack;
                    }
                };
            });
})();
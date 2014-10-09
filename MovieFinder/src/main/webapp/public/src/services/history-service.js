//
// history-service.js
//

(function() {
    'use strict';

    angular.module('movieFinder.services')
        .factory('history', function($location, $rootScope) {
            var history = [];

            // Listen for all successful route changes, so that we can
            // go back to previous route.
            $rootScope.$on('$routeChangeSuccess', function () {
                history.push($location.$$path);
            });

            return {
                'goBack': function () {
                    if (history.length) {
                        $location.path(history.splice(-1)[0]);
                    } else {
                        $location.path('/');
                    }
                }
            };
        });
})();
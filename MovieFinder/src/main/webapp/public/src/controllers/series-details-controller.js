//
// series-details-controller.js
// Controller for the series details view.
//
(function () {
    'use strict';
    angular.module('movieFinder.controllers')
            .controller('SeriesDetailsCtrl', function (seriesDetailsCtrlResolve, $location) {
                this.series = seriesDetailsCtrlResolve;

                this.goTo = function (path) {
                    $location.path(path);
                };
            })
            
            // Resolve before SeriesDetailsCtrl
            .factory('seriesDetailsCtrlResolve', function($route, seriesService) {
                return function() {
                    return seriesService.getById($route.current.params.id);
                };
            });
})();



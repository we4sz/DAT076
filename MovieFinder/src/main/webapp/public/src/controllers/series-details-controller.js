//
// series-details-controller.js
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
            
            // Resolve before MovieCtrl
            .factory('seriesDetailsCtrlResolve', function($route, seriesService) {
                return function() {
                    return seriesService.getById($route.current.params.id);
                };
            });
})();



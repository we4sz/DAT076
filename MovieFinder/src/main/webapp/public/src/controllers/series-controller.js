//
// series-controller.js
// Controller for the series list view.
//
(function () {
    'use strict';
    angular.module('movieFinder.controllers')
            .controller('SeriesCtrl', function (seriesService, seriesCtrlResolve, $location) {
                var _this = this;
                this.series = seriesCtrlResolve.series;

                this.filterSeries = function (rating) {
                    seriesService.getListByFilter({
                        imdbRating: rating
                    }).then(function (data) {
                        _this.series = data;
                    });
                };

                this.goTo = function (path) {
                    $location.path(path);
                };
            })
            
            // Resolve before SeriesCtrl
            .factory('seriesCtrlResolve', function(seriesService) {
                return function() {
                    return seriesService.getList().then(function(data) {
                        return {
                            series: data
                        };
                    });
                };
            });
})();



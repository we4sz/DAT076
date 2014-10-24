//
// movie-details-controller.js
// Holds the MovieDetailsCtrl, controller for the details screen.
//
(function () {
    'use strict';
    angular.module('movieFinder.controllers')
            .controller('MovieDetailsCtrl', function (movieDetailsCtrlResolve, $sce) {
                this.movie = movieDetailsCtrlResolve;

                this.video = function () {
                    return $sce.trustAsResourceUrl('api/files/stream/' + movieDetailsCtrlResolve.id);
                };

            })
            
            // Resolve before MovieDetailsCtrl
            .factory('movieDetailsCtrlResolve', function($route, movieService) {
                return function() {
                    return movieService.getById($route.current.params.id);
                };
            });
})();
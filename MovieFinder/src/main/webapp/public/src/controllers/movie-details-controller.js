//
// view-movies-controller.js
// Holds the MovieDetailsCtrl, controller for the details screen.
//
(function () {
    'use strict';
    angular.module('movieFinder.controllers')
            .controller('MovieDetailsCtrl', function (movieDetailsCtrlData, $sce) {
                this.movie = movieDetailsCtrlData;

                this.video = function () {
                    return $sce.trustAsResourceUrl("api/files/stream/" + movieDetailsCtrlData.id);
                };
                
            });
})();
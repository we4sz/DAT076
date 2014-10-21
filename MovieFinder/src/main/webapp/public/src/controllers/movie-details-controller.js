//
// view-movies-controller.js
// Holds the MovieDetailsCtrl, controller for the details screen.
//
(function () {
    'use strict';
    angular.module('movieFinder.controllers')
            .controller('MovieDetailsCtrl', function (movieDetailsCtrlData) {
                this.movie = movieDetailsCtrlData;
            });
})();
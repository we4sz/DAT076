//
// view-movies-controller.js
// Holds the HomeCtrl, controller for the home screen.
//
(function () {
    'use strict';
    angular.module('movieFinder.controllers')
            .controller('HomeCtrl', function (movie, homeCtrlData) {
                var _this = this;
                this.movies = homeCtrlData.movies;
                
                this.filterMovies = function(rating) {
                    movie.getMoviesByFilter({imdbRating: rating}).then(function (data) {
                        _this.movies = data;
                    });
                    
                };
      
            });
})();
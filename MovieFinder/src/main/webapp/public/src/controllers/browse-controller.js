//
// view-movies-controller.js
// Holds the BrowseCtrl, controller for the browsing screen.
//
(function () {
    'use strict';
    angular.module('movieFinder.controllers')
            .controller('BrowseCtrl', function (movie, browseCtrlData) {
                var _this = this;
                this.movies = browseCtrlData.movies;
                
                this.filterMovies = function(rating) {
                    movie.getMoviesByFilter({rating: rating}).then(function (data) {
                        _this.movies = data;
                    });
                    
                };
      
            });
})();



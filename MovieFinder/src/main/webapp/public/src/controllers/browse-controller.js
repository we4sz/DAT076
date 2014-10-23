//
// view-movies-controller.js
// Holds the BrowseCtrl, controller for the browsing screen.
//
(function () {
    'use strict';
    angular.module('movieFinder.controllers')
            .controller('BrowseCtrl', function (movie, browseCtrlData, $location) {
                var _this = this;
                this.movies = browseCtrlData.movies;

                this.filterMovies = function (rating) {
                    movie.getMoviesByFilter({imdbRating: rating}).then(function (data) {
                        _this.movies = data;
                    });

                };

                this.goTo = function (path, movie) {
                    $location.path(path+movie.id);
                };
            });
})();



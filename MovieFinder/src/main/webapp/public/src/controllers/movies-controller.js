//
// movies-controller.js
// Holds the MoviesCtrl, controller for the movie list view.
//
(function () {
    'use strict';
    angular.module('movieFinder.controllers')
            .controller('MoviesCtrl', function (movieService, moviesCtrlResolve, $location) {
                var _this = this;
                this.movies = moviesCtrlResolve.movies;

                this.filterMovies = function (rating, runtime) {
                    movieService.getListByFilter({
                        imdbRating: rating,
                        runtime: runtime
                    }).then(function (data) {
                        _this.movies = data;
                    });
                };

                this.goTo = function (path) {
                    $location.path(path);
                };
            })
            
            // Resolve before MoviesCtrl
            .factory('moviesCtrlResolve', function(movieService) {
                return function() {
                    return movieService.getList().then(function(data) {
                        return {
                            movies: data
                        };
                    });
                };
            });
})();



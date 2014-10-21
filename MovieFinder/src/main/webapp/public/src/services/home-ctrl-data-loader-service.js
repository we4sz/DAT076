//
// home-ctrl-data-loader-service.js
// Service for loading a set of movies, to be used in the resolve of controller.
//

(function() {
    'use strict';

    angular.module('movieFinder.services')
        .factory('homeCtrlDataLoader', function(movie) {
            return function() {
                return movie.getMovies().then(function(data) {
                    return {
                        movies: data
                    };
                });
            };
        });
})();


//
// movie-details-ctrl-data-loader-service.js
// Service for loading a set of movies, to be used in the resolve of controller.
//

(function() {
    'use strict';

    angular.module('movieFinder.services')
        .factory('movieDetailsCtrlDataLoader', function($route, movie) {
            return function() {
                return movie.getMovieById($route.current.params.id);
            };
        });
})();
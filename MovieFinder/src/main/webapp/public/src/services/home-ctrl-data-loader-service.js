//
// home-ctrl-data-loader-service.js
// Service for loading a set of movies, to be used in the resolve of controller.
//

(function() {
    'use strict';

    angular.module('movieFinder.services')
        .factory('homeCtrlDataLoader', function($q, movie) {
            return function() {
                return $q(function(resolve, reject){
                    movie.getMovies().success(function(data){
                        resolve({
                            movies: data
                        });
                    }).error(function(err){
                        reject(err);
                    });
                });
            };
        });
})();


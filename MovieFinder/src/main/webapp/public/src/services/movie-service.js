//
// movie-service.js
// Service for retrieving movies.
//

(function() {
    'use strict';

    angular.module('movieFinder.services')
        .factory('movie', function($http) {

            return {
                'getMovies': function() {
                    return $http.get('public/movieData.json');
                },
                /**
                 * Fetches movies from the server filtered by filter param
                 * @param {Object} filter a key-value object of filters to apply to the request
                 * @returns {Promise}
                 */
                'getMoviesByFilter': function(filter) {
                    var qs = [];
                    for(var key in filter) {
                        if(filter.hasOwnProperty(key) && angular.isDefined(filter[key])) {
                            qs.push(key + '=' + filter[key]);
                        }
                    }
                    var queryString = '?' + qs.join('&');
                                       
                    return $http.get('public/movieData.json' + queryString);
                }
            };
        });
})();
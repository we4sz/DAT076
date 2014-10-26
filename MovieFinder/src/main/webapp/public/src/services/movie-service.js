//
// movie-service.js
// Service for retrieving movies.
//

(function () {
    'use strict';

    angular.module('movieFinder.services')
        .factory('movieService', function (MediaService) {
            
            var mediaService = new MediaService('api/files/movies/');
            
            return {
                'getList': function () {
                    return mediaService.getList();
                },
                'getListByFilter': function (filter) {
                    return mediaService.getListByFilter(filter);
                },
                'getById': function (id) {
                    return mediaService.getById(id);
                }
            };
        });
})();
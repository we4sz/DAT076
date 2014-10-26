//
// episode-service.js
// A service for fetching information about an episode of a series.
//

(function () {
    'use strict';

    angular.module('movieFinder.services')
        .factory('episodeService', function (MediaService) {
            
            var mediaService = new MediaService('api/files/episodes/');
            
            return {
                'getById': function (id) {
                    return mediaService.getById(id);
                }
            };
        });
})();
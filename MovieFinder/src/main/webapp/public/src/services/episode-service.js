//
// episode-service.js
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
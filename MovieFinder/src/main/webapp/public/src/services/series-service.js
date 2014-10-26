//
// series-service.js
// Service for retrieving series.
//
(function () {
    'use strict';

    angular.module('movieFinder.services')
        .factory('seriesService', function (MediaService) {
            
            var mediaService = new MediaService('api/files/series/');
            
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
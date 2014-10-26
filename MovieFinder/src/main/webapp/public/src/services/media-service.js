//
// media-service.js
// Service for retrieving media from the REST api.
//

(function () {
    'use strict';

    angular.module('movieFinder.services')
        .factory('MediaService', function ($http, $q) {
            
            /**
             * Class for fetching lists/single media objects
             * from the REST API.
             * @param {String} mediaUrl The base URL of the API resource.
             */
            function MediaService(mediaUrl) {
                this.mediaUrl = mediaUrl;
            }
            
            /**
             * Tries to return a list of objects from the API.
             * @return {Promise} A promise resolved with a list of object
             *                   on success, or rejected with an error if
             *                   the request failed.
             */
            MediaService.prototype.getList = function() {
                return this.getListByFilter(null);
            };
            
            /**
             * Tries to return a list of objects from the API filtered
             * by the filter object parameter.
             * @param  {Object} filter Map of strings or objects which will be 
             *                         turned to ?key1=value1&key2=value2
             * @return {Promise} A promise resolved with a list of object
             *                   on success, or rejected with an error if
             *                   the request failed.
             */
            MediaService.prototype.getListByFilter = function(filter) {
                var url = this.mediaUrl;

                return $q(function (resolve, reject) {
                    $http.get(url, {params: filter}).success(function (data) {
                        resolve(data.content);
                    }).error(function (err) {
                        reject(err);
                    });
                });
            };
            
            /**
             * Tries to return a single object from the API with the
             * id specified.
             * @param  {int} id
             * @return {Promise} A promise resolved with an object on success, 
             *                   or rejected with an error if the request failed.
             */
            MediaService.prototype.getById = function(id) {
                var url = this.mediaUrl + id;
                return $q(function (resolve, reject) {
                        $http.get(url).success(function (data) {
                            resolve(data);
                        }).error(function (err) {
                            reject(err);
                        });
                    });
                };
                
            return MediaService;
        });
})();
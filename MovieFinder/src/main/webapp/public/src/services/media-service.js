//
// media-service.js
// Service for retrieving media from the rest api.
//

(function () {
    'use strict';

    angular.module('movieFinder.services')
        .factory('MediaService', function ($http, $q) {
            
            function MediaService(mediaUrl) {
                this.mediaUrl = mediaUrl;
            }
            
            MediaService.prototype.getList = function() {
                var url = this.mediaUrl;
                return $q(function (resolve, reject) {
                        $http.get(url).success(function (data) {
                            resolve(data.content);
                        }).error(function (err) {
                            reject(err);
                        });
                    });
            };
            
            MediaService.prototype.getListByFilter = function(filter) {
                var qs = [];
                for (var key in filter) {
                    if (filter.hasOwnProperty(key) && angular.isDefined(filter[key]) && filter[key] !== null) {
                        qs.push(key + '=' + filter[key]);
                    }
                }
                var url = this.mediaUrl + '?' + qs.join('&');
                
                return $q(function (resolve, reject) {
                    $http.get(url).success(function (data) {
                        resolve(data.content);
                    }).error(function (err) {
                        reject(err);
                    });
                });
            };
            
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
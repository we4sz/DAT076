//
// admin-service.js
// Service for admin specific tasks.
//

(function () {
    'use strict';

    angular.module('movieFinder.services')
            .factory('adminService', function ($http, $q) {
                return {
                    'addPath': function (path) {
                        return $http.post('api/admin/addPath', {listeningPath: path});
                    },
                    'removePath': function(id) {
                        return $http.delete('api/admin/removePath/' + id);
                    },
                    'getPaths': function() {
                        return $q(function (resolve, reject) {
                            $http.get('api/admin/getPaths').success(function (data) {
                                resolve(data.content);
                            }).error(function (err) {
                                reject(err);
                            });
                        });
                    }
                };
            });
})();

//
// user-service.js
// A representation of a user and various methods related to user, such as login and logout.
//

(function () {
    'use strict';

    angular.module('movieFinder.services')
            .factory('admin', function ($http) {
                return {
                    'addPath': function (path) {
                        return $http.post('api/admin/addPath', {listeningPath: path});
                    },
                };
            });
})();
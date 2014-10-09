//
// user-constants.js
// Constants that has to do with users.
//

(function () {
    'use strict';

    angular.module('movieFinder.constants')
            .constant('USER_ROLES', {
                ADMIN: 'ADMIN',
                VIEWER: 'VIEWER',
                ANY: 'ANY'
            });
})();
//
// http-interceptors-config.js
// File for setting up any app http interceptors.
//

(function() {
    'use strict';

    angular.module('movieFinder').config(function($httpProvider) {
        // Authentication interceptor for 401 and 403 responses
        $httpProvider.interceptors.push('authInterceptor');
    });
})();
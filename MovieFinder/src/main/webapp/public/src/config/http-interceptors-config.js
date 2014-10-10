//
// http-interceptors-config.js
// File for setting up any app http interceptors.
//

(function() {
    'use strict';

    angular.module('movieFinder').config(function($httpProvider) {
        // User interceptor for 401 responses
        $httpProvider.interceptors.push('authInterceptor');
    });
})();
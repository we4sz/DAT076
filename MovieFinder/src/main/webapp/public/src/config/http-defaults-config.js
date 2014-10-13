//
// http-defaults-config.js
// File for modifying the http defaults
//

(function() {
    'use strict';

    angular.module('movieFinder').config(function($httpProvider) {

        // Get the CSRF token name and value. These are writen by the
        // server when the index file is requested
        var csrfHeader = angular.element(document.querySelector('meta[name="_csrf_header"]')).attr('content');
        var csrfValue = angular.element(document.querySelector('meta[name="_csrf"]')).attr('content');


        // Set up $http to provide the csrf header on all POST, PUT and 
        // DELETE requests made.
        if(csrfHeader && csrfValue) {
            $httpProvider.defaults.headers.post[csrfHeader] = csrfValue;
            $httpProvider.defaults.headers.put[csrfHeader] = csrfValue;
            $httpProvider.defaults.headers.delete[csrfHeader] = csrfValue;
        }
    });
})();

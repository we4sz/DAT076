//
// app-routes-config.js
// Contains the route mapping for the app.
//

(function () {
    'use strict';

    angular.module('movieFinder')
            .config(function ($routeProvider, USER_ROLES, AUTH_LOGIN_PATH) {
                $routeProvider
                        .when('/', {
                            templateUrl: 'partials/home.html'
                        })
                        .when(AUTH_LOGIN_PATH, {
                            templateUrl: 'partials/login.html',
                            controller: 'LoginViewCtrl as loginViewCtrl'
                        })
                        .when('/admin', {
                            template: '<h1>admin</h1>',
                            resolve: {
                                auth: function(authHelper) {
                                    return authHelper.restrictRoute([USER_ROLES.ADMIN]);
                                }
                            }
                        })
                        .when('/viewer', {
                            template: '<h1>viewer</h1>',
                            resolve: {
                                auth: function(authHelper) {
                                    return authHelper.restrictRoute([USER_ROLES.ADMIN, USER_ROLES.VIEWER]);
                                }
                            }
                        })
                        .otherwise({
                            redirectTo: '/'
                        });
            });
})();
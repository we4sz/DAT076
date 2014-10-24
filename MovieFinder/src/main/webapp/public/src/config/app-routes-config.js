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
                            templateUrl: 'partials/welcome.html',
                        }).when('/browse', {
                            templateUrl: 'partials/browse.html',
                            controller: 'BrowseCtrl as browseCtrl',
                            resolve: {
                                'browseCtrlData' : function(browseCtrlDataLoader) {
                                    return browseCtrlDataLoader();
                                },
                                auth: function(authHelper) {
                                    return authHelper.restrictRoute([USER_ROLES.ADMIN, USER_ROLES.VIEWER]);
                                }
                            }
                        }).when('/movies', {
                            templateUrl: 'partials/movies.html',
                            controller: 'MoviesCtrl as moviesCtrl',
                            resolve: {
                                'moviesCtrlData' : function(moviesCtrlDataLoader) {
                                    return moviesCtrlDataLoader();
                                },
                                auth: function(authHelper) {
                                    return authHelper.restrictRoute([USER_ROLES.ADMIN, USER_ROLES.VIEWER]);
                                }
                            }
                        }).when('/series', {
                            templateUrl: 'partials/series.html',
                            controller: 'MoviesCtrl as moviesCtrl',
                            resolve: {
                                'moviesCtrlData' : function(moviesCtrlDataLoader) {
                                    return moviesCtrlDataLoader();
                                },
                                auth: function(authHelper) {
                                    return authHelper.restrictRoute([USER_ROLES.ADMIN, USER_ROLES.VIEWER]);
                                }
                            }
                        }).when('/movie/:id', {
                            templateUrl: 'partials/movie-details.html',
                            controller: 'MovieDetailsCtrl as movieDetailsCtrl',
                            resolve: {
                                'movieDetailsCtrlData' : function(movieDetailsCtrlDataLoader) {
                                    return movieDetailsCtrlDataLoader();
                                },
                                auth: function(authHelper) {
                                    return authHelper.restrictRoute([USER_ROLES.ADMIN, USER_ROLES.VIEWER]);
                                }
                            }
                            
                        })
                        .when(AUTH_LOGIN_PATH, {
                            templateUrl: 'partials/login.html',
                            controller: 'LoginViewCtrl as loginViewCtrl'
                        })
                        .when('/admin', {
                            templateUrl: 'partials/admin.html',
                            controller: 'AdminCtrl as adminCtrl',
                            resolve: {
                                auth: function(authHelper) {
                                    return authHelper.restrictRoute([USER_ROLES.ADMIN, USER_ROLES.VIEWER]);
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
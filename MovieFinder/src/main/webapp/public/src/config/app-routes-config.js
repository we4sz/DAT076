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
                            templateUrl: 'partials/welcome.html'
                        }).when('/movies', {
                            templateUrl: 'partials/movies.html',
                            controller: 'MoviesCtrl as moviesCtrl',
                            resolve: {
                                'moviesCtrlResolve' : function(moviesCtrlResolve) {
                                    return moviesCtrlResolve();
                                },
                                auth: function(authHelper) {
                                    return authHelper.restrictRoute([USER_ROLES.ADMIN, USER_ROLES.VIEWER]);
                                }
                            }
                        }).when('/movie/:id', {
                            templateUrl: 'partials/movie-details.html',
                            controller: 'MovieDetailsCtrl as movieDetailsCtrl',
                            resolve: {
                                'movieDetailsCtrlResolve' : function(movieDetailsCtrlResolve) {
                                    return movieDetailsCtrlResolve();
                                },
                                auth: function(authHelper) {
                                    return authHelper.restrictRoute([USER_ROLES.ADMIN, USER_ROLES.VIEWER]);
                                }
                            }
                            
                        }).when('/series', {
                            templateUrl: 'partials/series.html',
                            controller: 'SeriesCtrl as seriesCtrl',
                            resolve: {
                                'seriesCtrlResolve' : function(seriesCtrlResolve) {
                                    return seriesCtrlResolve();
                                },
                                auth: function(authHelper) {
                                    return authHelper.restrictRoute([USER_ROLES.ADMIN, USER_ROLES.VIEWER]);
                                }
                            }
                        }).when('/series/:id', {
                            templateUrl: 'partials/series-details.html',
                            controller: 'SeriesDetailsCtrl as seriesDetailsCtrl',
                            resolve: {
                                'seriesDetailsCtrlResolve' : function(seriesDetailsCtrlResolve) {
                                    return seriesDetailsCtrlResolve();
                                },
                                auth: function(authHelper) {
                                    return authHelper.restrictRoute([USER_ROLES.ADMIN, USER_ROLES.VIEWER]);
                                }
                            }
                        }).when('/series/:seriesId/episode/:episodeId', {
                            templateUrl: 'partials/episode-details.html',
                            controller: 'EpisodeDetailsCtrl as episodeDetailsCtrl',
                            resolve: {
                                'episodeDetailsCtrlResolve' : function(episodeDetailsCtrlResolve) {
                                    return episodeDetailsCtrlResolve();
                                },
                                auth: function(authHelper) {
                                    return authHelper.restrictRoute([USER_ROLES.ADMIN, USER_ROLES.VIEWER]);
                                }
                            }
                        }).when('/admin', {
                            templateUrl: 'partials/admin.html',
                            controller: 'AdminCtrl as adminCtrl',
                            resolve: {
                                'adminCtrlResolve': function (adminCtrlResolve) {
                                    return adminCtrlResolve();
                                },
                                auth: function(authHelper) {
                                    return authHelper.restrictRoute([USER_ROLES.ADMIN]);
                                }
                            }
                        }).when(AUTH_LOGIN_PATH, {
                            templateUrl: 'partials/login.html',
                            controller: 'LoginViewCtrl as loginViewCtrl'
                        }).otherwise({
                            redirectTo: '/'
                        });
            });
})();
//
// episode-details-controller.js
//
(function () {
    'use strict';
    angular.module('movieFinder.controllers')
            .controller('EpisodeDetailsCtrl', function (episodeDetailsCtrlResolve, $sce) {
                console.log(episodeDetailsCtrlResolve);
                
                this.series = episodeDetailsCtrlResolve.seriesData;
                this.episode = episodeDetailsCtrlResolve.episodeData;

                this.video = function () {
                    return $sce.trustAsResourceUrl('api/files/stream/' + episodeDetailsCtrlResolve.id);
                };

            })
            
            // Resolve before EpisodeDetailsCtrl
            .factory('episodeDetailsCtrlResolve', function($route, $q, episodeService, seriesService) {
                return function() {
                    var episodeReq = episodeService.getById($route.current.params.episodeId);
                    var seasonReq = seriesService.getById($route.current.params.seriesId);
                    return $q.all([episodeReq, seasonReq]).then(function(result) {
                        return {
                            episodeData: result[0],
                            seriesData: result[1]
                        };
                    });
                };
            });
})();
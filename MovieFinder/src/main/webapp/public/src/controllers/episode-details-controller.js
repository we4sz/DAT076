//
// episode-details-controller.js
// Controller for the detailed episode view.
//
(function () {
    'use strict';
    angular.module('movieFinder.controllers')
            .controller('EpisodeDetailsCtrl', function (episodeDetailsCtrlResolve, $sce) {                
                this.series = episodeDetailsCtrlResolve.seriesData;
                this.episode = episodeDetailsCtrlResolve.episodeData;

                // Must add video as trusted resource, see 
                // https://docs.angularjs.org/api/ng/service/$sce
                this.video = function () {
                    return $sce.trustAsResourceUrl('api/files/episodes/' + this.episode.id + '/stream/');
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
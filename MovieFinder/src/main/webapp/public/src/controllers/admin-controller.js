//
// view-movies-controller.js
// Holds the BrowseCtrl, controller for the browsing screen.
//
(function () {
    'use strict';
    angular.module('movieFinder.controllers')
            .controller('AdminCtrl', function (admin) {
                var _this = this;
                
                this.path = '';
                this.error = undefined;
                this.added = undefined;
                this.addPath = function(){
                    
                    admin.addPath(_this.path).success(function(){
                        _this.error = undefined;
                        _this.added = 'Successfully added the path';
                    }).error(function(){
                        _this.added = undefined;
                        _this.error = 'Not a valid path or already exists';
                    });
                };
                
                
            });
})();





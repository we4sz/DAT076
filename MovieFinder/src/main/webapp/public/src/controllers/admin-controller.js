//
// admin-controller.js
// The controller for the admin view.
//
(function () {
    'use strict';
    angular.module('movieFinder.controllers')
            .controller('AdminCtrl', function ($scope, adminService, adminCtrlResolve) {
                var _this = this;

                this.paths = adminCtrlResolve;               
                this.error = null;
                this.addingPath = false;

                this.addPath = function() {
                    this.addingPath = true;

                    adminService.addPath($scope.models.addPath).success(function(addedPath) {
                        _this.error = null;
                        _this.paths.push(addedPath);
                        _this.addingPath = false;
                        $scope.models.addPath = null;
                    }).error(function() {
                        _this.error = 'Not a valid path or already exists';
                        _this.addingPath = false;
                    });
                };

                this.removePath = function(pathObj) {
                    adminService.removePath(pathObj.id).success(function() {
                        _this.paths = _this.paths.filter(function(p) {
                            return (p.id !== pathObj.id);
                        });
                    }).error(function() {
                        _this.error = 'Could not remove path';
                    });
                };
            })

            // Resolve before AdminCtrl
            .factory('adminCtrlResolve', function(adminService) {
                return function() {
                    return adminService.getPaths();
                };
            });
})();





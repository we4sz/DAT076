//
// nav-controller.js
// Contains the controller for the nav-bar.
//

(function () {
    'use strict';

    angular.module('movieFinder.controllers')
            .controller('NavCtrl', function ($scope, $modal, user) {
                var _this = this;

                var signInModal;

                this.error = {
                    signIn: ''
                };

                this.showSignInModal = function () {
                    this.error.signIn = '';
                    
                    signInModal = $modal({
                        scope: $scope,
                        template: 'partials/modals/sign-in.html',
                        container: 'body'
                      });
                };

                this.login = function (username, password) {
                    user.login(username, password).success(function(){
                        signInModal.$promise.then(signInModal.hide);
                    }).error(function(){
                        _this.error.signIn = 'Some error message';
                    });
                };

                this.logout = function() {
                    user.logout();
                };

            });
})();
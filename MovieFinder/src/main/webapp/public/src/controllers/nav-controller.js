//
// nav-controller.js
// Contains the controller for the nav-bar.
//

(function () {
    'use strict';

    angular.module('movieFinder.controllers')
            .controller('NavCtrl', function ($scope, $rootScope, $modal, AUTH_EVENTS, history, user) {
                var _this = this;

                var signInModal;

                var hideSignInModal = function() {
                    if(signInModal) {
                        signInModal.$promise.then(signInModal.hide);
                    }
                };

                this.error = {
                    signIn: ''
                };

                this.modalState = null;

                // Shows a sign in modal. If force is true, the modal
                // will not be dismissible and will instead present a
                // go back option.
                // State is a string that is used in the modal template
                // to show the user why the modal is shown.
                this.showSignInModal = function (force, state) {
                    hideSignInModal();

                    this.error.signIn = '';
                    this.modalState = state;
                    this.modalForce = force;
                    
                    signInModal = $modal({
                        scope: $scope,
                        template: 'partials/modals/sign-in.html',
                        container: 'body',
                        keyboard: force ? false : true,
                        backdrop: force ? 'static' : true
                      });
                };

                this.login = function (username, password) {
                    user.login(username, password).success(function(){
                        hideSignInModal();
                    }).error(function(){
                        _this.error.signIn = 'Some error message';
                    });
                };

                this.logout = function() {
                    user.logout();
                };

                this.goBack = history.goBack;

                $scope.$on(AUTH_EVENTS.loginRequired, function(){
                    _this.showSignInModal(true, 'loginRequired');
                });

                $scope.$on(AUTH_EVENTS.forbidden, function(){
                    _this.showSignInModal(true, 'forbidden');
                });

                $rootScope.$on('$routeChangeStart', function () {
                    hideSignInModal();
                });

            });
})();
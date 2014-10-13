//
// login-view-controller.js
// Contains the controller for the login view.
//

(function () {
    'use strict';

    angular.module('movieFinder.controllers')
            .controller('LoginViewCtrl', function ($scope, authHelper, user) {
                var _this = this;

                // If we get here without a reason and the user is
                // already signed in, redirect back
                if(!authHelper.getLoginReason() && user.isLoggedIn()) {
                    authHelper.redirectToAttemptRoute();
                }

                // Have to watch instead of just get as the login might
                // trigger a second loginReason without a full route reload.
                // For example; auth-loginRequired -> "user logs in as non-auth role" 
                // -> auth-forbidden.
                $scope.$watch(authHelper.getLoginReason, function(newReason) {
                    _this.loginReason = newReason;
                });

                this.error = {
                    signIn: ''
                };

                this.login = function(username, password) {
                    user.login(username, password).success(function(){
                        delete $scope.signInUsername;
                        delete $scope.signInPassword;
                        $scope.signInForm.$setPristine();
                        authHelper.redirectToAttemptRoute();
                    }).error(function(data, status){
                        if(status === 401) {
                            _this.error.signIn = 'That username/password combination does not exist.';
                        } else {
                            _this.error.signIn = 'An unexpected error occurred.';
                        }
                    });
                };
            });
})();
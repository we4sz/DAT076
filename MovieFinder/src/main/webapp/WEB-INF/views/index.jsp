<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" ng-app="movieFinder" ng-controller="AppCtrl as appCtrl" ng-strict-di>
    
    <head>
        <base href="${pageContext.request.contextPath}/" />
        
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- TODO: Description! -->
        <meta name="description" content="">

        <!-- TODO: Favicon -->
        <!-- <link rel="icon" type="image/png" href="/img/favicon.png" /> -->

        <!-- <title ng:bind-template="{{'Movie Finder' : appCtrl.pageTitle}}"> -->
        <title>Movie Finder</title>

        <!-- Bootstrap -->
        <link href="resources/bower_components/bootstrap/dist/css/bootstrap.css" rel="stylesheet">

        <!-- App main styles -->
        <link href="resources/css/main.css" rel="stylesheet">
    </head>
    <body class="no-js">

        <!-- The view area, where we load our views into -->
        <div class="container" ng-view ng-hide="appCtrl.loading.isLoading || appCtrl.error.isError"></div>

        <!-- If the user does not have javascript our site will not work very well -->
        <noscript>
        <div class="container hide-js text-center">
            <div class="row">
                <div class="col-sm-6 col-md-4 col-md-offset-4 col-sm-offset-3">
                    <h1>No Javascript Detected</h1>

                    <p>Please <a href="http://www.enable-javascript.com/" target="_blank">Enable Javascript</a> in your
                        browser.</p>
                </div>
            </div>
        </div>
        </noscript>

        <!-- If we encounter an error loading a view, show it -->
        <div class="container" ng-show="appCtrl.error.isError">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <error error-text="{{appCtrl.error.errorMessage}}"></error>
                </div>
            </div>
        </div>


        <!-- Detect JS -->
        <script>document.body.className = document.body.className.replace('no-js', 'js');</script>

        <!-- JQuery -->
        <script src="resources/bower_components/jquery/dist/jquery.js"></script>

        <!-- Bootstrap js -->
        <script src="resources/bower_components/bootstrap/dist/js/bootstrap.js"></script>

        <!-- Angular core -->
        <script src="resources/bower_components/angular/angular.js"></script>
        <script src="resources/bower_components/angular-route/angular-route.js"></script>

        <!-- App -->
        <script src="resources/src/app.js"></script>
        <!--  App Config -->
        <script src="resources/src/config/app-routes-config.js"></script>
        <!-- App controllers -->
        <script src="resources/src/controllers/app-controller.js"></script>
        <!-- App directives -->
        <script src="resources/src/directives/error-directive.js"></script>
        <!-- App filters -->
        <!-- App services -->    
    </body>
</html>
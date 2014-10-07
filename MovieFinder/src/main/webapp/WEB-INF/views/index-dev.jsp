<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" ng-app="movieFinder" ng-controller="AppCtrl as appCtrl">

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
        <title>DEV - Movie Finder</title>

        <!-- 3rd party combined css, defined in gulpfile.js as "external_styles" -->
        <link href="build/css/lib.min.css" rel="stylesheet">

        <!-- App styles -->
        <link href="public/css/main.css" rel="stylesheet">

    </head>
    <body class="no-js">

        <!-- The view area, where we load our views into -->
        <div class="container" ng-view ng-hide="appCtrl.error.isError"></div>

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

        <!-- Reminder that this is the dev version (i.e. not using the built js files) -->
        <script>console.log('%c=== DEV VERSION ===', 'color: #FF0000');</script>

        <!-- Detect JS -->
        <script>document.body.className = document.body.className.replace('no-js', 'js');</script>

        <!-- 3rd party combined scripts, defined in gulpfile.js as "external_scripts"-->
        <script src="build/js/lib.min.js"></script>

        <!-- App scripts -->
        <script src="public/src/app.js"></script>
        <!-- Config -->
        <script src="public/src/config/app-routes-config.js"></script>
        <script src="public/src/config/http-interceptors-config.js"></script>
        <!-- Services -->
        <script src="public/src/services/auth-interceptor-service.js"></script>
        <script src="public/src/services/user-service.js"></script>
        <!-- Controllers --> 
        <script src="public/src/controllers/app-controller.js"></script>
        <script src="public/src/controllers/home-controller.js"></script>
        <!-- Directives -->
        <script src="public/src/directives/error-directive.js"></script>
    </body>
</html>
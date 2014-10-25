<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" ng-app="movieFinder" ng-controller="AppCtrl as appCtrl" ng-csp>

    <head>
        <base href="${pageContext.request.contextPath}/" />

        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!-- CSRF token -->
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        
        <!-- User data -->
        <meta name="_user_username" content="${sessionScope.user.username}" />
        <meta name="_user_role" content="${sessionScope.user.role}" />

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
        <!-- Loading animation -->
        <link href="public/css/loading.css" rel="stylesheet">
        <!-- Info Details -->
        <link href="public/css/info-details.css" rel="stylesheet">
        <!-- Welcome -->
        <link href="public/css/welcome.css" rel="stylesheet">
        <!-- Browsing -->
        <link href="public/css/browsing.css" rel="stylesheet">

    </head>
    <body class="no-js">

        <%@ include file="partials/index-content.html" %>

        <!-- Reminder that this is the dev version (i.e. not using the built js files) -->
        <script src="public/src/util/dev-version-reminder.js"></script>
        <!-- Script replacing "no-js" with "js" class on body if js is enabled -->
        <script src="public/src/util/javascript-detection.js"></script>

        <!-- 3rd party combined scripts, defined in gulpfile.js as "external_scripts"-->
        <script src="build/js/lib.min.js"></script>

        <!-- App scripts -->
        <script src="public/src/app.js"></script>
        <!-- Config -->
        <script src="public/src/config/app-routes-config.js"></script>
        <script src="public/src/config/http-defaults-config.js"></script>
        <script src="public/src/config/http-interceptors-config.js"></script>
        <!-- Constants -->
        <script src="public/src/constants/user-constants.js"></script>
        <script src="public/src/constants/auth-constants.js"></script>
        <!-- Services -->
        <script src="public/src/services/auth-interceptor-service.js"></script>
        <script src="public/src/services/auth-helper-service.js"></script>
        <script src="public/src/services/history-service.js"></script>
        <script src="public/src/services/user-service.js"></script>
        <script src="public/src/services/admin-service.js"></script>
        <script src="public/src/services/movie-service.js"></script>
        <script src="public/src/services/media-service.js"></script>
        <script src="public/src/services/series-service.js"></script>
        <script src="public/src/services/episode-service.js"></script>
        <!-- Controllers --> 
        <script src="public/src/controllers/app-controller.js"></script>
        <script src="public/src/controllers/admin-controller.js"></script>
        <script src="public/src/controllers/nav-controller.js"></script>
        <script src="public/src/controllers/login-view-controller.js"></script>
        <script src="public/src/controllers/movies-controller.js"></script>
        <script src="public/src/controllers/movie-details-controller.js"></script>
        <script src="public/src/controllers/series-controller.js"></script>
        <script src="public/src/controllers/series-details-controller.js"></script>
        <script src="public/src/controllers/episode-details-controller.js"></script>
        <!-- Directives -->
        <script src="public/src/directives/error-directive.js"></script>
    </body>
</html>
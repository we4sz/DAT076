<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" ng-app="movieFinder" ng-controller="AppCtrl as appCtrl" ng-strict-di ng-csp>

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
        
        <meta name="description" content="Scan, browse and stream your personal movie and series library">

        <!-- TODO: Favicon -->
        <!-- <link rel="icon" type="image/png" href="/img/favicon.png" /> -->

        <!-- <title ng:bind-template="{{'Movie Finder' : appCtrl.pageTitle}}"> -->
        <title>Movie Finder</title>

        <link href="build/css/lib.min.css" rel="stylesheet">
        <link href="build/css/app.min.css" rel="stylesheet">

    </head>
    <body class="no-js">

        <%@ include file="partials/index-content.html" %>

        <script src="build/js/lib.min.js"></script>
        <script src="build/js/app.min.js"></script>
        <script src="build/js/templates.js"></script>
    </body>
</html>
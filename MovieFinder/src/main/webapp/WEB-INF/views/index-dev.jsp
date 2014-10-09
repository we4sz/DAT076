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

        <%@ include file="partials/index-content.html" %>

        <!-- Reminder that this is the dev version (i.e. not using the built js files) -->
        <script>console.log('%c=== DEV VERSION ===', 'color: #FF0000');</script>

        <!-- Inject user session state if available -->
        <c:if test="${sessionScope.user != null}">
        <script>
            window.session_user = {
                username: '${sessionScope.user.username}',
                role: '${sessionScope.user.role}'
            }
        </script>
        </c:if>

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
        <script src="public/src/controllers/nav-controller.js"></script>
        <!-- Directives -->
        <script src="public/src/directives/error-directive.js"></script>
    </body>
</html>
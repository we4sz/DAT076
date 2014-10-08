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

        <link href="build/css/lib.min.css" rel="stylesheet">
        <link href="build/css/app.min.css" rel="stylesheet">

    </head>
    <body class="no-js">

        <%@ include file="partials/index-content.html" %>

        <c:if test="${sessionScope.user != null}">
        <script>
            window.session_user = {
                username: '${sessionScope.user.username}',
                role: '${sessionScope.user.role}'
            }
        </script>
        </c:if>

        <script src="build/js/lib.min.js"></script>
        <script src="build/js/app.min.js"></script>
        <script src="build/js/templates.js"></script>
    </body>
</html>
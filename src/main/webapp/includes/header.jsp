<%--
  Created by IntelliJ IDEA.
  User: kylehebert
  Date: 5/1/15
  Time: 12:58 PM
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
<head>
    <meta charset="utf-8">
    <title>Exquisite Lyrics</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="<c:url value='/stylesheets/main.css'/>">
</head>

<body>

<header>
    <img src="<c:url value='/images/logo.png'/>"
         alt="Exquisite Lyrics Logo">
    <h2>Without tradition, art is a flock of sheep without a shepherd. Without innovation, it is a corpse.</h2>
</header>
<nav id="nav_bar">
    <ul>
        <li><a href="<c:url value='/index.jsp'/>">
            New Search</a></li>
        <li><a href="<c:url value='/corpse.jsp'/>">
            View Corpse</a></li>
    </ul>
</nav>
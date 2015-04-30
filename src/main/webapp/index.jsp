<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kylehebert
  Date: 4/29/15
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

  <title>Exquisite Lyrics</title>
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

  <!-- Optional theme -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

  <!-- Latest compiled and minified JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body>
    <H1>Search for a Lyric Snippet</H1>
    <c:if test="${message != null}">
    <p><i>${message}</i></p>
    </c:if>

    <form action = "exquisite"  method= "post">
      <input type = "hidden" name = "action" value = "result">
      <label>Track:</label>
      <input type ="text" name="track" value = "${searchResult.track}" />
      <label>Artist:</label>
      <input type ="text" name ="artist" value = "${searchResult.artist}"/>
      <input type ="submit" value="Search">
    </form>

</body>
</html>

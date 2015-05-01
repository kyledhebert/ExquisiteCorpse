<%--
  Created by IntelliJ IDEA.
  User: kylehebert
  Date: 4/28/15
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Results</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body>
    <h1>Search Result</h1>
    <p>You searched for ${searchResult.track} by ${searchResult.artist}. </p>

    <label>Snippet:</label>
    <span>${searchResult.snippet}</span><br>

    <p>To search again use the New Search button below, or your browser's back button.</p>

    <form action = "" method = get>
        <input type = "hidden" name = "action" value = "search">
        <input type = "submit" value = "New Search">
    </form>

    <form action="exquisite" method="post">
        <input type="hidden" name="include" value="true">
        <input type="hidden" name="action" value="add">
        <input type="submit" value="Add Snippet">
    </form>

    <form action = "exquisite" method="post">
        <input type = "hidden" name = "action" value = "view">
        <input type = "submit" value = "View Corpse">
    </form>




</body>
</html>

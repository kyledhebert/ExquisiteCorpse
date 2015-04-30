<%--
  Created by IntelliJ IDEA.
  User: kylehebert
  Date: 4/28/15
  Time: 5:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Corpse</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body>
    <h1>Your Corpse</h1>
    <p>This is just some placeholder text.</p>

    <form action = "" method = get>
        <input type = "hidden" name = "action" value = "search">
        <input type = "submit" value = "New Search">
    </form>

    <form action = "" method = get>
        <input type = "hidden" name = "action" value = "result">
        <input type = "submit" value = "Back to Result">
    </form>

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: kylehebert
  Date: 4/28/15
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp" />

<h2>Search Result</h2>
<p>You searched for ${searchResult.track} by ${searchResult.artist}. </p>


<p><span>${searchResult.snippet}</span></p>

<p>To search again use the New Search button below, or your browser's back button.</p>

<form action="exquisite" method="post">
    <input type="hidden" name="action" value="add">
    <input type="submit" value="Add Snippet">
</form>

<form action = "exquisite" method = "get">
    <input type = "hidden" name = "action" value = "search">
    <input type = "submit" value = "New Search">
</form>

<form action = "exquisite" method="post">
    <input type = "hidden" name = "action" value = "view">
    <input type = "submit" value = "View Corpse">
</form>

</body>
</html>

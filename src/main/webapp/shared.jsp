<%--
  Created by IntelliJ IDEA.
  User: kylehebert
  Date: 5/4/15
  Time: 5:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.googlecode.objectify.Key"%>
<%@ page import="com.googlecode.objectify.ObjectifyService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/includes/header.jsp" />

<h2>An Exquisite Lyrics</h2>

<table>
    <c:forEach var="corpseLyric" items="${sharedCorpse.corpseLyrics}">
        <tr>
            <td>${corpseLyric.snippet}</td>
        </tr>
    </c:forEach>
</table>

<h2>Create Your Own</h2>
<form action="exquisite" method="post">
    <input type="hidden" name="action" value="reset">
    <input type="submit" value="Create New Corpse">
</form>

<c:import url="/includes/footer.jsp" />


<%--
  Created by IntelliJ IDEA.
  User: kylehebert
  Date: 4/28/15
  Time: 5:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.jsp" />

<h2>Your Corpse</h2>
<!-- need to create empty corpse condition here -->


<p><i>${message}</i></p>
<table>
<c:forEach var="corpseLyric" items="${corpse.corpseLyrics}">
<tr>
    <td>${corpseLyric.snippet}</td>
    <td>
        <form action ="exquisite" method="post">
            <input type="hidden" name="snippetID" value="${corpseLyric.snippetID}">
            <input type="hidden" name="action" value="remove">
            <input type="submit" value="Remove Lyric">
        </form>
    </td>
</tr>
</c:forEach>
</table>

<p>To remove a lyric, click on the Remove button.</p>

    <form action = "exquisite" method = "post">
        <input type = "hidden" name = "action" value = "search">
        <input type = "submit" value = "Add Another Lyric">
    </form>

    <form action = "exquisite" method="post">
        <input type = "hidden" name ="action" value ="share">
        <input type ="submit" value="Share on Twitter">
    </form>

<c:import url="/includes/footer.jsp" />


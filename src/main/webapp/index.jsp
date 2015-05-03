<%--
  Created by IntelliJ IDEA.
  User: kylehebert
  Date: 4/29/15
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/includes/header.jsp" />

    <H1>Start Building an Exquisite Corpse</H1>
    <p>Exquisite corpse, also known as exquisite cadaver (from the original French term cadavre exquis) or rotating corpse, is a method by which a collection of words or images is collectively assembled</p>
    <p>You can build your own Exquisite Corpse using the lyrics from your favorite artists and bands. Just search for a lyric snippet below to get started.</p>
    <c:if test="${message != null}">
    <p><i>${message}</i></p>
    </c:if>

    <form action = "exquisite"  method= "post">
      <input type = "hidden" name = "action" value = "result">
      <label>Track:</label>
      <input type ="text" name="track" value = "${searchResult.track}" tabindex="1"/>
      <label>Artist:</label>
      <input type ="text" name ="artist" value = "${searchResult.artist}" tabindex="2"/>
      <input type ="submit" value="Search" tabindex="3">
    </form>

</body>
</html>

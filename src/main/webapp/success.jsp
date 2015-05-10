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

<H1>You've Shared Your Corpse!</H1>
<p>You can view your corpse at this url:</p>
<p>${sharedURL}</p>
<p>Or use the button below to build a new one.</p>
<form action="exquisite" method="post">
    <input type="hidden" name="action" value="reset">
    <input type="submit" value="Create New Corpse">
</form>


<c:import url="/includes/footer.jsp" />


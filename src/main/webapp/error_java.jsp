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


    <h1>Java Error</h1>
    <p>Sorry, Java has thrown an exception.</p>
    <p>To continue, click the Back button or select one of the options from the menu above.</p>

    <h2>Details</h2>
    <code>
        ${pageContext.errorData.servletName} threw a <br>
        ${pageContext.exception}<br>
    </code>

<c:import url="/includes/footer.jsp" />


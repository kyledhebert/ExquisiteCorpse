<%--
  Created by IntelliJ IDEA.
  User: kylehebert
  Date: 4/29/15
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Java Error</title>
</head>
<body>
<h1>And We Still Haven't Found What You're Looking For</h1>
<p>Sorry, Java has thrown an exception.</p>
<p>To continue, click the Back button.</p>

<h2>Details</h2>
<p>Type: {pageContext.exception["class"]</p>
<p>Message: [pageContext.exception.message}</p>

</body>
</html>

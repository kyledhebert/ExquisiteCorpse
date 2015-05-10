<%--
  Created by IntelliJ IDEA.
  User: kylehebert
  Date: 5/5/15
  Time: 2:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Get the current year for copyright notice -->
<%@ page import ="java.util.GregorianCalendar, java.util.Calendar" %>
<% GregorianCalendar currentDate = new GregorianCalendar();
   int currentYear = currentDate.get(Calendar.YEAR);
%>

<footer>
    <p>&copy; Copyright <%= currentYear %>  Kyle Hebert
        All rights reserved.</p>
    <p>Lyrics provided by <a href="<c:url value='http://www.musixmatch.com/resources'/> ">MusixMatch</a></p>
</footer>
</body>
</html>

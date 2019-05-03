<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: md
  Date: 3/1/19
  Time: 12:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>models.Project<</title>
</head>
<body>
    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">
    <ul>
        <%--<c:forEach items="${project}" var="prject">--%>


        <li>
            <%--"${Id}"--%>

            id:  <%= request.getAttribute("id") %>
        </li>
        <%--<c:forEach items="${requestScope.get(project).getId()}" begin="1" end="6">--%>
        <li>
            title:  <%= request.getAttribute("title") %>
        </li>
        <li>
            description: <%= request.getAttribute("description") %>
        </li>
        <li>
            deadline:  <%= request.getAttribute("deadline") %>
        </li>
        <li>
            budget:"  <%= request.getAttribute("budget") %>
        </li>
        <li>imageURL:
            <img src= "<%= request.getAttribute("imageURL") %> "style="width: 50px; height: 50px;">
        </li>


        <%--</c:forEach>--%>
        <%--</c:forEach>--%>

    </ul>
    <%
        if ((boolean)request.getAttribute("indoors")) {
    %>
    <form action="" method="post">
        <%--&lt;%&ndash;@declare id="bidamount"&ndash;%&gt;<label for="bidAmount">Bid Amount:</label>--%>
        <input type="number" name="bidAmount" >
          <input type="hidden" name="user_id" value="1" >

        <button>Submit</button>
    </form>
    <%
    }
    else {
    %>
    <%
        }
    %>
    <%--<jsp:useBean id="indoors" scope="request" type="java.lang.Boolean"/>--%>
    <%--<c:set var="indoors" value="${indoors.booleanValue}"/>--%>

    <%--</c:if>--%>




</body>
</html>

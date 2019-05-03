<%@ page import="models.Skills" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: md
  Date: 3/1/19
  Time: 9:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    ArrayList<Skills> allSkill = new ArrayList<Skills>();
    allSkill = (ArrayList<Skills>)request.getAttribute("skills");
%>
<head>
    <title>User</title>
</head>
<body>
    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">
    <ul>
        <li>
            id: <%= request.getAttribute("id") %>
        </li>
        <li>
            First Name: <%= request.getAttribute("firstname") %>
        </li>
        <li>
             Last Name: <%= request.getAttribute("lastname")%>
        </li>
        <li>
            Job Title: <%= request.getAttribute("jobtitle")%>
        </li>
        <li>
            Bio: <%= request.getAttribute("bio")%>
        </li>
        <%
            if (!(boolean)request.getAttribute("other")) {
        %>
        <%

            for (Skills skill:allSkill) {%>
        <li>
            <form action="" method="post">
                <%= skill.getName() %>
                <%= skill.getPoints()%>
                <button type="submit">  indors me </button>
                <input type="hidden" name="id" value=<%= skill.getName() %> >
                <input type="hidden" name="id_user" value="2" >
                <input type="hidden" name="id_indors" value="1" >
                <%--<% skill.addpoint("1"); %>">Click Me!</button>--%>
            </form>

        </li>


        <%}%>
        <%
        }
        else {
        %>
        <%
            for (Skills skill:allSkill) {%>
        <li>
            <form action="" method="post">
                <%= skill.getName() %>
                <%= skill.getPoints()%>
                <button type="submit"> delete skill</button>
                <input type="hidden" name="id" value=<%= skill.getName() %> >
                <input type="hidden" name="id_user" value="1" >
                <%--<% skill.addpoint("1"); %>">Click Me!</button>--%>
            </form>

        </li>
        <%}%>
        <li>
            <button type="submit"> <a href="../addSkill"> add skills</a></button>
        </li>
        <%
            }
        %>


    </ul>
</body>
</html>

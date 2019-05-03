<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Skills" %>
<%@ page import="models.Register" %>
<%@ page import="models.MyUser" %><%--
  Created by IntelliJ IDEA.
  User: md
  Date: 2019-03-02
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%
    Register activeUser= MyUser.getInstance().getMyusers_reg(0);
    ArrayList<Skills> allSkill = new ArrayList<Skills>();
    allSkill = (ArrayList<Skills>)request.getAttribute("skillsList");
%>
<head>
    <title>Add Skill</title>
</head>
<body>
    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">
    <table style=width:100%>
        <tr>
            <th>Name</th>
        </tr>
       <%
        for (Skills skill:allSkill) {
               if(activeUser.hasSkill(skill)){
                   continue;
               }%>
            <tr>
                <td>
                    <form action="" method="post">
                    <%= skill.getName() %>
                    <button  id = <%= skill.getName() %> type="submit" >Click Me!</button>
                    <input type="hidden" name="id" value=<%= skill.getName() %> >
                    <input type="hidden" name="id_user" value="1" >
                    </form>

                </td>
            </tr>
        <%}%>

        <script>
            function addSkillFunc(id) {

                document.getElementById(id).style.display ="none";
            }
        </script>


</body>
</html>

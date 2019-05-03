<%@ page import="models.Register" %>
<%@ page import="models.MyUser" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    ArrayList<Register> users = new ArrayList<Register>();
//    System.out.println(request.getAttribute("usersList"));
    users = (ArrayList<Register>) request.getAttribute("usersList");
%>
<head>
    <title>UsersInfo</title>
</head>
<body>
    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">
    <table style=width:100%>
        <tr>
            <th>id</th>
            <th>Name</th>
            <th>Job Title</th>
            </tr>
            <%
            for (Register user : users) {
                if(user.isActive()){
                    continue;
                }
            %>
                <tr>
                    <td><%= user.getId() %></td>
                    <td><%= user.getFirstName()+" "+user.getLastName() %></td>
                    <td><%= user.getJobTitle() %></td>
                </tr>
            <%
            }
         %>
        </table>
</body>
</html>

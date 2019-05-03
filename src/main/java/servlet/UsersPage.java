package servlet;


import models.MyUser;
import models.Register;
import org.json.JSONArray;
import org.json.JSONObject;
import servlet.mytools.Project_page_tool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/users/*")
public class UsersPage extends MyServlet {
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        JSONObject resp_massage = new JSONObject();

//        System.out.println("in servlet");

        ArrayList<Register> users = MyUser.getInstance().getUsers();
        if(users == null){
            System.out.println("null pointer");
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            resp_massage.put("massage","user found");
//            return;
//            response.sendError(403);
        }
        else {
            resp_massage.put("users",new JSONArray(users));

//            System.out.println("give array");
//            return;
//            System.out.println(users.toArray().toString() + "servlet");
//            request.setAttribute("usersList", users);
//            RequestDispatcher view = getServletContext().getRequestDispatcher("/UserPage.jsp");
//            view.forward(request, response);
        }
        PrintWriter out = response.getWriter();

        out.print(resp_massage);
    }
}

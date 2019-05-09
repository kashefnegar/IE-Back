package servlet;


import com.google.common.io.CharStreams;
import models.*;
import org.json.JSONObject;
import servlet.mytools.Project_page_tool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signup/*")
public class SignUp extends MyServlet{
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        JSONObject resp_massage = new JSONObject();
        String[] path = request.getRequestURI().split("/");

    }

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        JSONObject resp_massage = new JSONObject();

        String body = CharStreams.toString(request.getReader());
        System.out.println(body);
        Utility tool = new Utility();
        if (tool.sign_up_query_type( tool.getuser(body))){
            resp_massage.put("massage","user are signed up");
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            resp_massage.put("massage","change user");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }


//        MyUser users = MyUser.getInstance();

        PrintWriter out = response.getWriter();
        out.print(resp_massage);


    }

}

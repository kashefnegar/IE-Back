package servlet;


import com.google.common.io.CharStreams;
import datalayer.Login_Query;
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

@WebServlet("/login/*")
public class Login extends MyServlet{
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
//        System.out.println(body);
        if( new Login_Query(body).passwordcheak()){
            resp_massage.put("massage","logined");
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            resp_massage.put("massage","invalid username or password");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }


        PrintWriter out = response.getWriter();
        out.print(resp_massage);


    }

}

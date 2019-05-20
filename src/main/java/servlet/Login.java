package servlet;


import com.google.common.io.CharStreams;
import datalayer.Login_Query;
import servlet.mytools.jwt_handler;
import org.json.JSONObject;

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
        Login_Query user_ = new Login_Query(body);
        if( user_.passwordcheak()){
            resp_massage.put("massage","logined");
            response.setStatus(HttpServletResponse.SC_OK);
            resp_massage.put("jwt",new jwt_handler().createJWT(user_.get_username(),"joboonja","user",1000000));
        }
        else {
            resp_massage.put("massage","invalid username or password");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }


        PrintWriter out = response.getWriter();
        out.print(resp_massage);


    }

}

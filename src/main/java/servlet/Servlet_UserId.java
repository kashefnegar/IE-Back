package servlet;

import models.MyUser;
import models.Register;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( "/user/*")
public class Servlet_UserId extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
//        JSONObject resp_massage = new JSONObject();
//        Register user = MyUser.getInstance().FindUser(request.getParameter("id_user"));
//        if(testingUserather(request.getParameter("id_user"))){
//            user.delete_skill(request.getParameter("id"));
//            resp_massage.put("massage", "skill was deleted");
//            response.sendRedirect("../user/1");
//        }
//        else {
//            user.getskill(request.getParameter("id")).addpoint(request.getParameter("id_indors"));
//            response.sendRedirect("../user/2");
//        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] path = request.getRequestURI().split("/");
        Register user = MyUser.getInstance().FindUser(path[2]);
        response.setContentType("application/json;charset=UTF-8");
        JSONObject resp_massage = new JSONObject();
        JSONObject res_massage = new JSONObject();
//        System.out.println(path[1]);
        if(user==null){
           response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
           resp_massage.put("massage", "invalid request");
        }
        else{
           resp_massage.put("id", user.getId());
           resp_massage.put("firstname", user.getFirstName());
           resp_massage.put("lastname", user.getLastName());
           resp_massage.put("jobtitle",user.getJobTitle());
           resp_massage.put("bio", user.getBio());
           resp_massage.put("skills", user.getSkill());
           resp_massage.put("image", user.getProfilePictureURL());
//            System.out.println("fuck");
           resp_massage.put("other", testingUserather(path[2]));
//            RequestDispatcher view=getServletContext().getRequestDispatcher("/userprofile.jsp");
//            view.forward(request, response);
        }
        PrintWriter out = response.getWriter();
        out.print(resp_massage);

    }

    private boolean testingUserather(String id){
        if (id.equals("1")){
            return true;
        }
        else return false;
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("application/json");
        JSONObject resp_massage = new JSONObject();
        response.setStatus(HttpServletResponse.SC_OK);
        Register user = MyUser.getInstance().FindUser(request.getParameter("id_user"));
        if(testingUserather(request.getParameter("id_user"))){
            user.delete_skill(request.getParameter("id"));
            resp_massage.put("massage", "skill was deleted");
        }
        PrintWriter out = response.getWriter();
        out.print(resp_massage);

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("application/json");
        JSONObject resp_massage = new JSONObject();
        response.setStatus(HttpServletResponse.SC_OK);
        Register user = MyUser.getInstance().FindUser(request.getParameter("id_user"));
        if(!testingUserather(request.getParameter("id_user"))){
            user.getskill(request.getParameter("id")).addpoint(request.getParameter("id_indors"));
            resp_massage.put("massage", "skill was indore");
        }
        PrintWriter out = response.getWriter();
        out.print(resp_massage);

    }
}

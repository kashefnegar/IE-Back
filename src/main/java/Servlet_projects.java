import models.MyUser;
import models.Project;
import models.Projects;
import models.Register;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "Servlet_projects", urlPatterns = "/projects/*")
public class Servlet_projects extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("hiprj");
        response.setContentType("application/json;charset=UTF-8");
        JSONObject resp_massage = new JSONObject();
        if (Projects.getInstance().getProjects().size()==0){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp_massage.put("massage", "Projects cant be found");
        }
        else {
            ArrayList<Project> progect_can_see =new ArrayList<>();
            response.setStatus(HttpServletResponse.SC_OK);
            ArrayList<Project> allprjects = Projects.getInstance().getProjects();
            MyUser user = MyUser.getInstance();
            Register user_index = user.FindUser( ((Register)request.getAttribute("user")).getId() );

            for (int i=0; i<allprjects.size(); i++){
                if( Projects.getInstance().hasNecessarySkills(allprjects.get(i).getId(),
                       user_index) ){
                    progect_can_see.add( allprjects.get(i));

                }
            }


            resp_massage.put("Projects", new JSONArray(progect_can_see));
        }
//        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(resp_massage);

    }
}

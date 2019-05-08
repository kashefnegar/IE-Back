package servlet;

import models.Project;
import models.Utility;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/searchProject/*")
public class Project_search extends MyServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        JSONObject resp_massage = new JSONObject();

        String name = request.getParameter("Projectname");
        ArrayList<Project> prj = new Utility().search_project(name);
        if (prj == null) {
            System.out.println("null pointer");
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            resp_massage.put("massage", "no project found with this descriptions");
        }
        else {
            resp_massage.put("users", new JSONArray(prj));
        }
        PrintWriter out = response.getWriter();

        out.print(resp_massage);
    }
}


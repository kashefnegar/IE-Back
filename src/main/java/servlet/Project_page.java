package servlet;


import models.MyUser;
import models.Project;
import models.Projects;
import models.Register;
import org.json.JSONObject;
import servlet.mytools.Project_page_tool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/project/*")
public class Project_page extends MyServlet{
//    Project_page_tool project_page_tool_;
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        JSONObject resp_massage = new JSONObject();
        String[] path = request.getRequestURI().split("/");;
        Project_page_tool project_page_tool_ =new Project_page_tool(path[2]);
        if (project_page_tool_.getState()==200){
            response.setStatus(HttpServletResponse.SC_OK);
            resp_massage.put("id",project_page_tool_.getSelected().getId());
            resp_massage.put("title",project_page_tool_.getSelected().getTitle());
            resp_massage.put("description",project_page_tool_.getSelected().getDescription());
            resp_massage.put("deadline ",project_page_tool_.getSelected().getDeadline());
            resp_massage.put("budget",project_page_tool_.getSelected().getBudget());
            resp_massage.put("imageURL",project_page_tool_.getSelected().getImageURL());
            resp_massage.put("indoors" ,project_page_tool_.Cheakindorsment());
//            request.setAttribute("id",project_page_tool_.getSelected().getId());
//            request.setAttribute("title",project_page_tool_.getSelected().getTitle());
//            request.setAttribute("description",project_page_tool_.getSelected().getDescription());
//            request.setAttribute("deadline ",project_page_tool_.getSelected().getDeadline());
//            request.setAttribute("budget",project_page_tool_.getSelected().getBudget());
//            request.setAttribute("imageURL",project_page_tool_.getSelected().getImageURL());
//            request.setAttribute("indoors" ,project_page_tool_.Cheakindorsment());

//            RequestDispatcher view=getServletContext().getRequestDispatcher("/project_id.jsp");
//            request.mapperContextRootRedirectEnabled=true;
//            view.forward(request,response);
        }
        else {
            resp_massage.put("massage","unauthorized request");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        PrintWriter out = response.getWriter();
        out.print(resp_massage);

//        else response.sendError(project_page_tool_.getState());

    }

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject resp_massage = new JSONObject();
        Project project = Projects.getInstance().getProjectIndex(request.getRequestURI().split("/")[2]);
        project.addreq_1((Register)request.getAttribute("user"), Integer.parseInt( request.getParameter("bidAmount")));
        resp_massage.put("massage","request was added");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        out.print(resp_massage);
//        System.out.println(request.getRequestURI());
//        response.sendRedirect(request.getRequestURI());

    }

}

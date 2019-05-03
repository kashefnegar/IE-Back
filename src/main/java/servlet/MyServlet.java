package servlet;
import models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

//import static models.Projects.getInstance;

@WebServlet(name = "MyServlet")
public class MyServlet extends HttpServlet {
    MyServlet() {

    }
    public void init() throws ServletException {
//        MyUser myUser= MyUser.getInstance();
//        Projects myProjects;
//        myProjects = Projects.getInstance();
//        try {
//            myUser.adduser(new Utility().getDefultUser());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        AllSkills incSkills = AllSkills.getInstance();
//        incSkills.setUrl("http://142.93.134.194:8000/joboonja/skill");
//        incSkills.setAllskills();
//        myProjects.get_project_url("http://142.93.134.194:8000/joboonja/project");
//        System.out.println("Hi");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

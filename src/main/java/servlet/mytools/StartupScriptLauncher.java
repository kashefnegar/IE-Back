package servlet.mytools;

import datalayer.getProjectControler;
import models.AllSkills;
import models.MyUser;
import models.Projects;
import models.Utility;

import java.io.FileNotFoundException;
import java.util.Arrays;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupScriptLauncher implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        MyUser myUser= MyUser.getInstance();
        Projects myProjects;
        myProjects = Projects.getInstance();
        Utility myUtl = new Utility();
//        try {
//            myUser.adduser(myUtl.getDefultUser("/Users/md/Desktop/collage/IE/CA1/IE_1/hi/ca1/defultuser.json"));
//            myUser.adduser(myUtl.getDefultUser("D:/University/3971-2/IE/Projects/3/CA3/ca3/ca1/defultuser.json"));
            System.out.println("Hi2");
//            myUser.adduser(myUtl.getDefultUser("/Users/md/Desktop/collage/IE/CA1/IE_1/hi/ca1/defultuser2.json"));
//            myUser.adduser(myUtl.getDefultUser("D:/University/3971-2/IE/Projects/3/CA3/ca3/ca1/defultuser.json"));
            System.out.println("hi3");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        AllSkills incSkills = AllSkills.getInstance();
        incSkills.setUrl("http://142.93.134.194:8000/joboonja/skill");
        incSkills.setAllskills();
        getProjectControler round = new getProjectControler();
        round.beepForAnHour();
//        myProjects.get_project_url("http://142.93.134.194:8000/joboonja/project");
        System.out.println("Hi");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

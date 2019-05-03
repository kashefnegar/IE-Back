package models;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;

public class ProjectPage implements Page {
    private Projects myProjects;
    private ArrayList<Project> projects;
    private String projectsList ;
//    String projectsList = "";

    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException {
        String response =
                "<html><title>models.Projects</title>"
                +"<body><h3>You can See models.Projects below</h3>"
                        +"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"
                        +"<table style=width:100%>"
                        +"<tr>"
                            +"<th>id</th>"
                            +"<th>title</th>"
                            +"<th>description</th>"
                            +"<th>deadline</th>"
                            +"<th>budget</th>"
                        +"</tr>"
                        +projectsList
                +"</table>"
                +"</body>"
                + "</html>";
        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        httpExchange.getResponseHeaders().set("Content-Type", "text/html");
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    public ProjectPage() {
        myProjects = Projects.getInstance();

        projects = Projects.getInstance().getProjects();
        projectsList = FindWantedProjects();
    }

    private String FindWantedProjects() {
        MyUser myuser = MyUser.getInstance();
        StringBuilder list = new StringBuilder();


        ArrayList<Skills> userskill = myuser.getMyusers_reg(0).getSkill();
        for (Project project : projects) {
            String temp = "";
            int neededskills = 0;
            for (Skills prjskill : project.getNeedskil()) {
                int index = myuser.getMyusers_reg(0).findSkill(prjskill.getName());
                if (index != -1) {
                    if(prjskill.getPoints() <= userskill.get(index).getPoints()) {
                        neededskills++;
                    }

                }
               else break;
            }
            if (neededskills == project.getNeedskil().size()) {
                temp = "<tr>"
                        + "<td>" + project.getId() + "</td>"
                        + "<td>" + project.getTitle() + "</td>"
                        + "<td>" + project.getDescription() + "</td>"
                        + "<td>" + project.getDeadline() + "</td>"
                        + "<td>" + project.getBudget() + "</td>"
                        + "</tr>";
            }
            list.append(temp);
        }
        return list.toString();
    }
}

package models;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ProjectId implements Page {
    String projectsList = "";
    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException {
        boolean hasNeeded;
        String response;
        String[] path = httpExchange.getRequestURI().getPath().split("/");
        String id = path[3];
        hasNeeded = CheckAvailability(id);
        if(hasNeeded)
        {
            Project selected = Projects.getInstance().getProjectIndex(id);
            response = "<html><title>models.Project Info</title>"
                    + "<body>"
                    +"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"
                    +"<ul>"
                    +   "<li>id = " + selected.getId() + "</li>"
                    +   "<li>title = " + selected.getTitle() + "</li>"
                    +   "<li>description = " + selected.getDescription() + "</li>"
                    +   "<li>deadline = " + selected.getDeadline() + "</li>"
                    +   "<li>budget = " + selected.getBudget() + "</li>"
                    +   "<li>imageURL = " + selected.getImageURL() + "</li>"
                    + "</ul> "
                    +"</body>"
                    + "</html>";
            httpExchange.sendResponseHeaders(200, response.getBytes().length);
            httpExchange.getResponseHeaders().set("Content-Type", "text/html");
        }

        else{
            response = "<html>"
                    + "<body><h2>You don't have enough requirement</body>"
                    + "</html>";
            httpExchange.sendResponseHeaders(403, response.getBytes().length);
            httpExchange.getResponseHeaders().set("Content-Type", "text/html");
        }
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private boolean CheckAvailability(String id) {
        Projects myProjects = Projects.getInstance();
        Register myUser=MyUser.getInstance().getMyusers_reg(0);
        return myProjects.hasNecessarySkills(id,myUser);
    }
}
package models;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

public class UserPage implements Page {
    private String response = "";
    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException, ClassNotFoundException {
        String[] path = httpExchange.getRequestURI().getPath().split("/");
        String id = path[3];

        Register user = MyUser.getInstance().FindUser(id);
        if (user==null) throw new ClassNotFoundException();
        else
            {response = "<html><title>models.Project Info</title>"
                + "<body>"
                    +"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"
                +"<ul>"
                +   "<li>id = " + user.getId() + "</li>"
                +   "<li>first name = " + user.getFirstName() + "</li>"
                +   "<li>last name = " + user.getLastName() + "</li>"
                +   "<li>job title = " + user.getJobTitle() + "</li>"
                +   "<li>bio = " + user.getBio() + "</li>"
                + "</ul> "
                +"</body>"
                + "</html>";
        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        httpExchange.getResponseHeaders().set("Content-Type", "text/html");
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
            }
    }
}
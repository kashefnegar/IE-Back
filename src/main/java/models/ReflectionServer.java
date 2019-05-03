package models;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.StringTokenizer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class ReflectionServer {
    public void startServer() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8082), 0);
        server.createContext("/CA2", new MyHandler());
        server.setExecutor(null);
        server.start();
    }

    class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            StringTokenizer tokenizer = new StringTokenizer(httpExchange.getRequestURI().getPath(), "/");
            String context = tokenizer.nextToken();
            String page = tokenizer.nextToken();
            Class<Page> pageClass;
            try {
                pageClass = (Class<Page>) Class.forName(page);
                Page newInstance = pageClass.getDeclaredConstructor().newInstance();
                newInstance.HandleRequest(httpExchange);
            } catch (ClassNotFoundException |
                    InstantiationException |
                    IllegalAccessException |
                    IllegalArgumentException |
                    InvocationTargetException |
                    NoSuchMethodException |
                    SecurityException e) {
                e.printStackTrace();
                String response =
                        "<html>"
                                + "<body>models.Page \""+ page + "\" not found.</body>"
                                + "</html>";
                httpExchange.sendResponseHeaders(404, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}

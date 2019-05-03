package models;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

public interface Page {
    void HandleRequest(HttpExchange httpExchange) throws IOException,ClassNotFoundException;
}

package datalayer;


import datalayer.dbConnection.BasicDBConnectionPool;
import datalayer.dbConnection.impl.SQLiteBasicDBConnectionPool;

public class ConnectionPool {

    private final static String dbURL = "jdbc:sqlite:D:\\University\\3971-2\\IE\\Projects\\7-1\\identifier.sqlite";
    private static SQLiteBasicDBConnectionPool instance;

    public static SQLiteBasicDBConnectionPool getInstance() {
        if (instance == null) {
            instance = new SQLiteBasicDBConnectionPool(1, 4 , dbURL);
        }
        return instance;
    }
}
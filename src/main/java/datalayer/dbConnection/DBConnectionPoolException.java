package datalayer.dbConnection;

public class DBConnectionPoolException extends RuntimeException {
    public DBConnectionPoolException(String message) {
        super(message);
    }
}

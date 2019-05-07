package datalayer;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * do not reinvent the wheel!!!
 *
 * you can use DBCP or other libraries.
 *
 * @see <a href="https://www.baeldung.com/java-connection-pooling">A Simple Guide to Connection Pooling in Java</a>
 *
 * */
public class DBCPDBConnectionPool {

//    private static BasicDataSource ds = new BasicDataSource();
//    private final static String dbURL = "jdbc:sqlite:taca7.db";
//    private final static String dbURL = "jdbc:sqlite:/Users/md/Desktop/collage/IE/CA1/IE_1/IE_7/identifier.sqlite";
//    private final static String dbURL = "jdbc:sqlite:D:\\University\\3971-2\\IE\\Projects\\7-1\\identifier.sqlite";


    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;


    static {
        config.setPoolName("SqlitePool");
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl("jdbc:sqlite:/Users/md/Desktop/collage/IE/CA1/IE_1/IE_7/identifier.sqlite");
        config.setMaxLifetime(60000);
        config.setIdleTimeout(45000);
        config.setMaximumPoolSize(50);
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private DBCPDBConnectionPool(){ }
}

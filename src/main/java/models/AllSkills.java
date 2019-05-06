package models;

import datalayer.ConnectionPool;
import datalayer.dbConnection.BasicDBConnectionPool;
import datalayer.dbConnection.impl.SQLiteBasicDBConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AllSkills {
    private static AllSkills ourInstance = new AllSkills();
    private static ArrayList<Skills> allskills = new ArrayList<>();
    private static String url ;

    public ArrayList<Skills> getAllskills() {
        return allskills;
    }

    public void setAllskills() {
        MyHttpURLConnection get_skills = new MyHttpURLConnection();
        try {
            get_skills.get_url(url);
            allskills = new Utility().getskilsnames( new Utility().jsonbufferstring(get_skills.getContent()));

            if(!cheaking_skillexit()) {
                SQLiteBasicDBConnectionPool sqlInstance = ConnectionPool.getInstance();
                Connection conn = sqlInstance.get();
                for (int i = 0; i < allskills.size(); i++) {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.executeUpdate("INSERT INTO Skill VALUES (i,allskills.get(i).getName())");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
//                connection.get().commit("INSERT INTO Skill VALUES("+String(i) +"," + allskills.get(i).getName()+")");
                }
                sqlInstance.release(conn);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void setUrl(String url) {
        AllSkills.url = url;
    }
    public  static AllSkills getInstance() {
        return ourInstance;
    }

    private AllSkills() {
    }
    public boolean cheaking_skillexit(){
        SQLiteBasicDBConnectionPool sqlInstance= ConnectionPool.getInstance();
        Connection conn = sqlInstance.get();
        try {
            Statement stmt =conn.createStatement();
            ResultSet rs =stmt.executeQuery("select count (*) FROM Skill ");
            sqlInstance.release(conn);
            if (rs.toString().equals("0")){
                return false;
            }
            else {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

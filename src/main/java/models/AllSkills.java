package models;

import datalayer.ConnectionPool;
import datalayer.DBCPDBConnectionPool;
import datalayer.dbConnection.impl.SQLiteBasicDBConnectionPool;
import datalayer.dbConnection.BasicDBConnectionPool;
import datalayer.dbConnection.impl.SQLiteBasicDBConnectionPool;
import datalayer.dbConnection.impl.SQLiteBasicDBConnectionPool;

import java.io.IOException;
import java.sql.*;
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
                Connection conn  =  DBCPDBConnectionPool. getConnection();

                for (int i = 0; i < allskills.size(); i++) {
                    try {
                        PreparedStatement prepStmt = conn.prepareStatement("insert into Skill (id, name) VALUES (?,? )");
                        prepStmt.setInt(1,i+1);
                        prepStmt.setString(2,allskills.get(i).getName());
                        prepStmt.executeUpdate();
//                        Statement stmt = conn.createStatement();
//                        stmt.executeQuery("INSERT" +
//                                " INTO Skill" +
//                                " VALUES (i+1,allskills.get(i).getName())");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
//                connection.get().commit("INSERT INTO Skill VALUES("+String(i) +"," + allskills.get(i).getName()+")");
                }
//                sqlInstance.release(conn);
                conn.close();
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
        Connection conn  = null;
        try {
            conn = DBCPDBConnectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Statement stmt =conn.createStatement();
            ResultSet rs =stmt.executeQuery("select count (*) FROM Skill ");

            if (rs.getString("count (*)").equals("0")){
                conn.close();
                return false;
            }
            else {
                conn.close();
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

}

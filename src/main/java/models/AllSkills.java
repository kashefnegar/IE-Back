package models;

//import datalayer.dbConnection.BasicDBConnectionPool;
//import datalayer.dbConnection.impl.SQLiteBasicDBConnectionPool;

import datalayer.HikariCPDataSource;

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
                Connection conn = HikariCPDataSource.getConnection();
                for (int i = 0; i < allskills.size(); i++) {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.executeUpdate("INSERT INTO Skill VALUES (i,allskills.get(i).getName())");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
    public boolean cheaking_skillexit() throws SQLException, ClassNotFoundException {
//        Connection conn = HikariCPDataSource.getConnection();
//        try {
//            Statement stmt = conn.createStatement();
//            ResultSet rs =stmt.executeQuery("select count (*) FROM Skill ");
//
//            if (rs.toString().equals("0")){
//                return false;
//            }
//            else {
//                return true;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
        String SQL_QUERY = "select count (*) FROM Skill";
//        List<Employee> employees = null;
        try (Connection con = HikariCPDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement( SQL_QUERY );
             ResultSet rs = pst.executeQuery();) {
            employees = new ArrayList<>();
            Employee employee;
            while ( rs.next() ) {
                employee = new Employee();
                employee.setEmpNo( rs.getInt( "empno" ) );
                employee.setEname( rs.getString( "ename" ) );
                employee.setJob( rs.getString( "job" ) );
                employee.setMgr( rs.getInt( "mgr" ) );
                employee.setHiredate( rs.getDate( "hiredate" ) );
                employee.setSal( rs.getInt( "sal" ) );
                employee.setComm( rs.getInt( "comm" ) );
                employee.setDeptno( rs.getInt( "deptno" ) );
                employees.add( employee );
            }
        }
    }

}

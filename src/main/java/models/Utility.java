package models;

import datalayer.ConnectionPool;
import datalayer.DBCPDBConnectionPool;
import datalayer.dbConnection.impl.SQLiteBasicDBConnectionPool;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Utility {

    public Utility() {
    }

    public JSONObject jsonstring(String commandData){
        JSONTokener tokener = new JSONTokener(commandData);
        return new JSONObject(tokener);
    }
    ArrayList<JSONObject> jsonbufferstring( StringBuffer commandData){
        ArrayList<JSONObject> jsonobjects = new ArrayList<>();
        JSONArray arr = new JSONArray(commandData.toString());
        for (int i=0; i<arr.length() ; i++){
            jsonobjects.add(arr.getJSONObject(i));
        }
        return jsonobjects;
    }
//    @SuppressWarnings("unchecked")
    public Register getDefultUser(String path)throws FileNotFoundException {
//        JSONTokener tokener = new JSONTokener(new FileReader("D:/University/3971-2/IE/Projects/3/CA3/ca3/ca1/defultuser.json"));
        JSONTokener tokener = new JSONTokener(new FileReader(path));
        JSONObject object = new JSONObject(tokener);
        ArrayList <Object> info = jsonparser(new ArrayList<>(Arrays.asList("id", "firstName", "lastName"
                                                , "jobTitle", "profilePictureURL", "skills", "bio")),object);
        return new Register((String)info.get(0),(String)info.get(1),(String)info.get(2),(String)info.get(3)
                            ,(String)info.get(4),(ArrayList<Skills>)info.get(5),(String)info.get(6));
    }

    public ArrayList<Object> jsonparser(ArrayList<String> data, JSONObject object)throws JSONException {
            ArrayList<Object> resultdata = new ArrayList<>();
            for (String aData : data) {
                switch (aData) {
                    case "skills":
                        resultdata.add(getskillsarrya(object));
                        break;
                    case "point":
                    case "bidAmount":
                    case "budget":
                        resultdata.add(object.getInt(aData));
                        break;
                    case "deadline":
                    case "creationDate":
                        resultdata.add(object.getLong(aData));
                        break;
                    default:
                        try {
                            resultdata.add(object.getString(aData));
                        } catch (JSONException e) {
                            resultdata.add(null);
                        }
                        break;
                }
        }
        return resultdata;
    }

    public ArrayList<Skills> getskillsarrya(JSONObject object) {
        ArrayList<Skills> skills = new ArrayList<>();
        JSONArray myskills = object.getJSONArray("skills");
        for (int i = 0; i < myskills.length(); i++) {
            skills.add(new Skills(myskills.getJSONObject(i)));
        }
        return skills;
    }

    public ArrayList<Skills> getskilsnames(ArrayList<JSONObject> nameskils){
        ArrayList<Skills> skills = new ArrayList<>();
        for (JSONObject nameskil : nameskils) {
            skills.add(new Skills(nameskil.getString("name"), 0));
        }
        return skills;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Project> getprject(ArrayList<JSONObject> projectlist) {
        ArrayList<Project> project = new ArrayList<>();


        for (JSONObject projasn : projectlist) {
            Connection conn  = null;
//            Connection   conn = DBCPDBConnectionPool. getConnection()
            ArrayList<Object> resultdata = jsonparser(new ArrayList<>(Arrays.asList("id", "title"
                    , "description", "imageUrl", "deadline", "skills", "budget","creationDate")), projasn);
            System.out.println((String) resultdata.get(0));
            try {
                conn = DBCPDBConnectionPool. getConnection();
                PreparedStatement prepStmt = conn.prepareStatement("insert into Project (id, title, description, imgURL, budget, deadline,creationDate) VALUES (?,?,?,?,?,?,?)");
                prepStmt.setString(1,(String) resultdata.get(0));
                prepStmt.setString(2,(String) resultdata.get(1));
                prepStmt.setString(3,(String) resultdata.get(2));
                prepStmt.setString(4,(String) resultdata.get(3));
                prepStmt.setLong(5,(long) resultdata.get(4));
                prepStmt.setInt(6,(int) resultdata.get(6));
                prepStmt.setLong(7,(long) resultdata.get(7));
                prepStmt.executeUpdate();
                conn.close();


            } catch (SQLException e) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }


//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
            for (int i=0; i<((ArrayList<Skills>)resultdata.get(5)).size() ; i++){
                System.out.println(i);
                Connection conn1 = null;
                try {
                    conn1 = DBCPDBConnectionPool. getConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {

                    PreparedStatement prepStmt = conn1.prepareStatement("insert into ProjectSkill (ProjectID, SkillID, Point)VALUES (?,?,?)");
                    prepStmt.setString(1,(String) resultdata.get(0));
                    int id=get_skilid((((ArrayList<Skills>) resultdata.get(5)).get(i).getName()));
                    if(id!=-1) {
                        prepStmt.setInt(2,id);
                    }
                    else{
                       conn1.close();
                        continue;

                    }
                    prepStmt.setInt(3,((ArrayList<Skills>) resultdata.get(5)).get(i).getPoints()) ;
                    prepStmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                finally {
                    try {
                        conn1.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            project.add(new Project((String) resultdata.get(0), (String) resultdata.get(1), (String) resultdata.get(2)
                    , (String) resultdata.get(3), (long) resultdata.get(4)
                    , (ArrayList<Skills>) resultdata.get(5), (int) resultdata.get(6)));
        }
        return project;
    }
    public int get_skilid(String name) throws SQLException {
        Connection conn  =  DBCPDBConnectionPool. getConnection();
        try {
            PreparedStatement prepStmt = conn.prepareStatement("select id FROM Skill where name=?");
            prepStmt.setString(1,name);
            ResultSet rs = prepStmt.executeQuery();

//            sqlInstance.release(conn);
            int result = rs.getInt("id");
            conn.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            conn.close();
//            sqlInstance.release(conn);
            return -1;

        }

    }
}

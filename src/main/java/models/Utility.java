package models;


import datalayer.DBCPDBConnectionPool;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Utility {
    byte[] salt;
    public Utility() {

    }
    // doesn't need change
    public JSONObject jsonstring(String commandData){
        JSONTokener tokener = new JSONTokener(commandData);
        return new JSONObject(tokener);
    }

    // doesn't need change
    ArrayList<JSONObject> jsonbufferstring( StringBuffer commandData){
        ArrayList<JSONObject> jsonobjects = new ArrayList<>();
        JSONArray arr = new JSONArray(commandData.toString());
        for (int i=0; i<arr.length() ; i++){
            jsonobjects.add(arr.getJSONObject(i));
        }
        return jsonobjects;
    }

//    @SuppressWarnings("unchecked")
//   !!!!!!!!!!!!!!!!!!!
    public Register getDefultUser(String path)throws FileNotFoundException {
//        JSONTokener tokener = new JSONTokener(new FileReader("D:/University/3971-2/IE/Projects/3/CA3/ca3/ca1/defultuser.json"));
        JSONTokener tokener = new JSONTokener(new FileReader(path));
        JSONObject object = new JSONObject(tokener);
        ArrayList <Object> info = jsonparser(new ArrayList<>(Arrays.asList("id", "firstName", "lastName"
                                                , "jobTitle", "profilePictureURL", "skills", "bio")),object);
        return new Register((String)info.get(0),(String)info.get(1),(String)info.get(2),(String)info.get(3)
                            ,(String)info.get(4),(ArrayList<Skills>)info.get(5),(String)info.get(6));
    }
    public Register getuser(String body)throws FileNotFoundException {
//        JSONTokener tokener = new JSONTokener(new FileReader("D:/University/3971-2/IE/Projects/3/CA3/ca3/ca1/defultuser.json"));
        JSONTokener tokener = new JSONTokener(body);
        JSONObject object = new JSONObject(tokener);

        ArrayList <Object> info = jsonparser(new ArrayList<>(Arrays.asList("id", "firstName", "lastName"
                , "jobTitle", "profilePictureURL", "bio","password")),object);
        return new Register((String)info.get(0),(String)info.get(1),(String)info.get(2),(String)info.get(3)
                ,(String)info.get(4),(String)info.get(5),(String)info.get(6));
    }

    // doesn't need change
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

    // doesn't need change
    public ArrayList<Skills> getskillsarrya(JSONObject object) {
        ArrayList<Skills> skills = new ArrayList<>();
        JSONArray myskills = object.getJSONArray("skills");
        for (int i = 0; i < myskills.length(); i++) {
            skills.add(new Skills(myskills.getJSONObject(i)));
        }
        return skills;
    }

// doesn't need change
    public ArrayList<Skills> getskilsnames(ArrayList<JSONObject> nameskils){
        ArrayList<Skills> skills = new ArrayList<>();
        for (JSONObject nameskil : nameskils) {
            skills.add(new Skills(nameskil.getString("name"), 0));
        }
        return skills;
    }

    @SuppressWarnings("unchecked")
//    changed to query type
    public ArrayList<Project> getprject(ArrayList<JSONObject> projectlist) {
        ArrayList<Project> project = new ArrayList<>();
        for (JSONObject projasn : projectlist) {
            Connection conn  = null;
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
        }
        return project;
    }

//    change to query type
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

    public ArrayList<Project>  search_project(String name){
        ArrayList<Project> proj_want = new ArrayList<>();
        try {
            Connection conn  =  DBCPDBConnectionPool. getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("select * FROM Project where name=? OR description=?");
            prepStmt.setString(1,name);
            prepStmt.setString(2,name);
            ResultSet rs =prepStmt.executeQuery();
            while (rs.next()){
                proj_want.add(new Project(
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("imgURL"),
                        rs.getLong("deadline"), ret_skill_prj_qury(rs.getString("id")),rs.getInt("budget"),
                        rs.getLong("creationDate")));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proj_want;

    }

    //    change to query type
    public  ArrayList<Skills> ret_skill_prj_qury(String id) throws SQLException {
        ArrayList<Skills> prSkill = new ArrayList<>();
        Connection conn = DBCPDBConnectionPool. getConnection();
        PreparedStatement prepStmt= conn.prepareStatement("select  sk.name,prs.Point \n" +
                "FROM\n" +
                "     ProjectSkill prs , Skill sk\n" +
                "WHERE\n" +
                "      ?= prs.ProjectID and\n" +
                "      sk.id =prs.SkillID");
        prepStmt.setString(1, id);
        ResultSet skills= prepStmt.executeQuery();
        while (skills.next()){
            prSkill.add(new Skills(skills.getString("name"),skills.getInt("Point")));
        }
        conn.close();
        return prSkill;

    }

    public boolean sign_up_query_type(Register user){
        try {
            System.out.println(user.getBio());
            Connection conn = DBCPDBConnectionPool. getConnection();
            PreparedStatement prepStmt= conn.prepareStatement("INSERT INTO User " +
                    "(id, firstName, lastName, jobTitle, ImgURL, bio,password) VALUES (?,?,?,?,?,?,?) ");
            prepStmt.setString(1,user.getId());
            prepStmt.setString(2,user.getFirstName());
            prepStmt.setString(3, user.getLastName());
            prepStmt.setString(4, user.getJobTitle());
            prepStmt.setString(5,user.getProfilePictureURL());
            prepStmt.setString(6,user.getBio());
            prepStmt.setString(7,user.getPassword());
            prepStmt.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String set_passeord_increption(String password){
        try {
            Connection conn = DBCPDBConnectionPool.getConnection();
            Statement stmt =conn.createStatement();
            ResultSet rs =stmt.executeQuery("select count (*),randomgenerat FROM information");
            if (rs.getString("count (*)").equals("0")){
                conn.close();
                System.out.println("password");
                SecureRandom random = new SecureRandom();
                this.salt =new byte[16];
                random.nextBytes(this.salt);
                Connection conn1 = DBCPDBConnectionPool.getConnection();
                PreparedStatement prepStmt1= conn1.prepareStatement("INSERT INTO information" +
                        " (randomgenerat) values (?)");
                prepStmt1.setString(1,this.salt.toString());
                prepStmt1.executeUpdate();
                conn1.close();
            }
            else {
               this.salt =rs.getString("randomgenerat").getBytes();
                conn.close();
            }
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            return factory.generateSecret(spec).getEncoded().toString();

        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return "";

    }

//    public void generat_pass(){
//
//    }

}

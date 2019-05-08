package models;

import datalayer.DBCPDBConnectionPool;
import org.json.JSONObject;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Projects {
    private static Projects ourInstance = new Projects();
    private ArrayList<Project> projects = new ArrayList<>();

    public static Projects getInstance() {
        return ourInstance;
    }

//    change to query type
    public  ArrayList<Project> getProjects(){
        Connection conn = null;
        try {
            conn = DBCPDBConnectionPool. getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("select pr.id ,pr.title ,pr.description,pr.imgURL, pr.deadline, pr.creationDate,pr.budget\n" +
                    "FROM\n" +
                    "     Project pr\n" +
                    "order by pr.creationDate\n");
            ResultSet projects =prepStmt.executeQuery();
            this.projects=project_list_from_sql(projects);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  this.projects;

    }

//    change to query type
    public ArrayList<Project>  project_list_from_sql( ResultSet rs){
        ArrayList<Project> projects_list = new ArrayList<>();
        try {
                System.out.println("!!!!!!!!!!!!!");
                String  id = "";

                while (rs.next()) {
                    System.out.println("hi");
                         projects_list.add( new Project(
                                 rs.getString("id"),
                                 rs.getString("title"),
                                 rs.getString("description"),
                                 rs.getString("imgURL"),
                                 rs.getLong("deadline"),
                                 new Utility().ret_skill_prj_qury(rs.getString("id")),rs.getInt("budget"),
                                 rs.getLong("creationDate")));

                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return projects_list;
        }
    }

    private Projects() {
    }



// use in addproject , addbid, auction change for next faze!!!!!!!!!!!!!
    private int indexofstring(String comperstring){
            for (int i=0; i<projects.size(); i++){
                if(projects.get(i).getTitle().equals(comperstring))
                    return i;
            }
            return -1;

    }

    // doesn't need change we dont use it any more
    public int getProjectIndexByID(String id){

        for (int i = 0 ; i < projects.size() ; i++) {
            if(projects.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

//    changed to query type
    public Project getProjectIndex(String id){

        Connection conn = null;
        try {
            conn = DBCPDBConnectionPool. getConnection();
            PreparedStatement prepStmt= conn.prepareStatement(
                    "SELECT * FROM Project WHERE id=?"
            );
            prepStmt.setString(1,id);
            ResultSet rs= prepStmt.executeQuery();
            return new Project(
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("imgURL"),
                    rs.getLong("deadline"),
                    new Utility().ret_skill_prj_qury(rs.getString("id")),rs.getInt("budget"),
                    rs.getLong("creationDate")
            );

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

//        int index = getProjectIndexByID(id);
//        if (index!= -1)
//            return this.projects.get(index);
//        else return null;
    }

//!!!!!!!!!!!!!!!!!!
    public boolean hasNecessarySkills(String  id , Register user){
        boolean projectFound = false;
        boolean enoughSkill = true;
        int index = 0;
        for (int i = 0 ; i < projects.size() ; i++)
        {
            if(projects.get(i).getId().equals(id)){
                projectFound = true;
                index = i;
                break;
            }
        }
        if(projectFound)
        {
            for (Skills temp : projects.get(index).getNeedskil()) {
                int skillIndex =  user.findSkill(temp.getName());
                if(skillIndex != -1) {
                    if (temp.getPoints() > user.getSkill().get(skillIndex ).getPoints())
                        enoughSkill = false;


                }
                else {
                    enoughSkill = false;
                }
            }
            return enoughSkill;
        }
        return false;

    }

    // doesn't need change
    public void get_project_url(String url){
        MyHttpURLConnection get_projects = new MyHttpURLConnection();
        try {
            get_projects.get_url(url);
            projects =new Utility().getprject(new Utility().jsonbufferstring(get_projects.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void addproject(Project newproject){
        int index = indexofstring(newproject.getTitle());
        if(index == -1) {
            projects.add(newproject);
        }
        else
            System.out.println("This models.Project Was Added Before");
    }

    boolean addbid(String commandData, MyUser myUser){
        JSONObject object = new Utility().jsonstring(commandData);
        int index = myUser.indexofstring(object.getString("biddingUser"));
        int index1 = indexofstring(object.getString("projectTitle"));
        if (index!= -1 && index1!=-1) {
            return projects.get(index1).addreq(myUser.getMyusers_reg(index),object);
        }
        else{
            if(index == -1){
                System.out.println("User Not Found");
            }
            else
                System.out.println("models.Project Not Found");
            return false;
        }
    }

    String auction(String commandData){
        JSONObject object = new Utility().jsonstring(commandData);
        int index1 = indexofstring(object.getString("projectTitle"));
        if (index1 != -1)
        {
            return projects.get(index1).auction();
        }
        return "models.Project not Found";

    }


}

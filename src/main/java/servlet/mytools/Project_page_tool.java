package servlet.mytools;

import models.MyUser;
import models.Project;
import models.Projects;
import models.Register;


public class Project_page_tool {
    private Project selected;
    private int state;
    private String userid;

    public Project_page_tool(String id){
        if(CheckAvailability(id))
        {
            this.selected =Projects.getInstance().getProjectIndex(id);
            this.userid = "1";
            this.state=200;
        }
        else {
            this.selected = null;
            this.userid = "1";
            this.state= 403;
        }

    }

    private boolean CheckAvailability(String id) {
        Projects myProjects = Projects.getInstance();
        Register myUser= MyUser.getInstance().getMyusers_reg(0);
        return myProjects.hasNecessarySkills(id,myUser);
    }

    public boolean Cheakindorsment(){
        try {
            for ( int i=0; i<this.selected.getUserREQ().size(); i++){
                if(this.selected.getUserREQ().get(i).getUserREQ().getId().equals(this.userid))
                    return false;
            }
            return true;
        }
        catch (NullPointerException e){
            return true;
        }

    }

    public Project getSelected() {
        return selected;
    }

    public int getState() {
        return state;
    }
}

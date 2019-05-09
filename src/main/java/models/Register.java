package models;

import java.util.ArrayList;

public class  Register {
    private String id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String profilePictureURL;
    private ArrayList<Skills> Skill;
    private String username;
    private String bio;
    private String password;

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    Register(String id, String firstName, String lastName, String jobTitle, String profilePictureURL, ArrayList<Skills> skill, String bio) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.profilePictureURL = profilePictureURL;
        this.Skill = skill;
        this.bio = bio;
    }

    Register(String id, String firstName, String lastName, String jobTitle, String profilePictureURL, String bio,String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.profilePictureURL = profilePictureURL;
        this.bio = bio;
        this.password = new Utility().set_passeord_increption(password);

    }

    public String getPassword(){
        return this.password;
    }
    public boolean isActive(){
        Register active = MyUser.getInstance().getMyusers_reg(0);
        if(username == active.getUsername() && id == active.getId()){
            return true;
        }
        return false;
    }
    public boolean hasSkill(Skills skill){
        for (Skills temp: Skill) {
            if(temp.getName().equals(skill.getName()))
                return true;
        }
        return false;
    }
    public String getUsername() {
        return username;
    }

    public ArrayList<Skills> getSkill() {
        return Skill;
    }
    public int findSkill(String name){
        for (int i = 0 ; i < Skill.size() ; i++) {
            if(Skill.get(i).getName().equals(name))
                return i;

        }
        return -1;
    }
    public Skills getskill(String name){
        int index=findSkill(name);
        if (index != -1)
            return this.Skill.get(index);
        else return null;
    }

    public void delete_skill(String sname){
        int index=findSkill(sname);
        if (index != -1){
            this.Skill.remove(index);
        }
    }
    public void addSkills(Skills rskill){
        this.Skill.add(rskill);
    }

    public String getId() { return id;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getBio() {
        return bio;
    }
}

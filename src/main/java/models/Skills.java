package models;

import org.json.JSONObject;

import java.util.ArrayList;

public class Skills {
    private String name;
    private int points;
    private ArrayList<String> insdorst = new ArrayList<>();
    Skills(JSONObject object) {
        this.name = object.getString("name");
        this.points = object.getInt("point");
    }

   public Skills(String name, int points) {
        this.name = name;
        this.points = points;
    }


    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public boolean addpoint(String id_user){
        if (this.insdorst.contains(id_user)){
            return false;
        }
        else {
            this.insdorst.add(id_user);
            this.points += 1;
            return true;
        }
    }

}

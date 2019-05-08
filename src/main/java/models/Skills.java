package models;

import org.json.JSONObject;

import java.util.ArrayList;

public class Skills {
    private String name;
    private int points;
    private ArrayList<String> insdorst = new ArrayList<>();
  // doesn't need change
    Skills(JSONObject object) {
        this.name = object.getString("name");
        this.points = object.getInt("point");
    }
    // doesn't need change
   public Skills(String name, int points) {
        this.name = name;
        this.points = points;
    }

    // doesn't need change
    public String getName() {
        return name;
    }

    // doesn't need change
    public int getPoints() {
        return points;
    }

    // doesn't need change
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

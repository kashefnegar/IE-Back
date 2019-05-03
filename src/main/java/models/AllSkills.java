package models;

import java.io.IOException;
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

}

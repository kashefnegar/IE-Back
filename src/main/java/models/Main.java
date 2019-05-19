package models;

import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class Main {

    public static void main(String[] args)throws Exception {
//        MyUser myUser=MyUser.getInstance();
//        Projects myProjects = Projects.getInstance();
//        myProjects.getProjects();
//        myUser.adduser(new Utility().getDefultUser("/Users/md/Desktop/collage/IE/CA1/IE_1/hi/ca1/defultuser.json"));
//        AllSkills incSkills = AllSkills.getInstance();
//        incSkills.setUrl("http://142.93.134.194:8000/joboonja/skill");
//        incSkills.setAllskills();
//        myProjects.get_project_url("http://142.93.134.194:8000/joboonja/project");

//        ReflectionServer server = new ReflectionServer();
//        server.startServer();
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        salt[0]=-56;
        salt[1]=71;
        salt[2]=-35;
        salt[3]=-109; salt[4]=-56;
        salt[5]=-56; salt[0]=-56; salt[0]=-56; salt[0]=-56;
        salt[0]=-56;
        salt[0]=-56;
        salt[0]=-56;
        salt[0]=-56;
        salt[0]=-56;




//        salt={-56, 71, -35, 109, -30, 18, 33, 124, -29, -116, -111, -9, 15, -85, -39, -59}
        random.nextBytes(salt);

//        System.out.println();
        System.out.println(Arrays.toString(salt));
        KeySpec spec = new PBEKeySpec ("1234".toCharArray() ,salt, 65536, 128);

    }

}

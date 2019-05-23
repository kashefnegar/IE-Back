package datalayer;

import models.Utility;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Login_Query {
    private String username;
    private String password;

    public String get_username() {
        return this.username;
    }

    public Login_Query(String body) {
        JSONObject info = new Utility().jsonstring(body);
        this.username = info.getString("username");
        this.password = info.getString("password");
    }

    public boolean passwordcheak() {

        Connection conn = null;
        try {
            conn = DBCPDBConnectionPool.getConnection();
            PreparedStatement prepStmt = conn.prepareStatement("SELECT password FROM User where id=?");
            prepStmt.setString(1, username);
            ResultSet rs = prepStmt.executeQuery();

            if (new BCryptPasswordEncoder().matches(this.password, rs.getString("password"))) {
                conn.close();
                return true;
            }
            conn.close();
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }


}

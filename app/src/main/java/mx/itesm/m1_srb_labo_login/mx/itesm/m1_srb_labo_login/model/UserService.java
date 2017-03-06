package mx.itesm.m1_srb_labo_login.mx.itesm.m1_srb_labo_login.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by saul on 9/2/2017.
 */

public class UserService implements Serializable {

    private ArrayList<User> users;

    public UserService(ArrayList<User> users) {
        this.users = users;
    }

    public boolean addUser(User user){
        boolean success = false;
        if (findUser(user.getUsername()) == null) {
            users.add(user);
            success = true;
        }
        return success;
    }

    public User findUser(String username){
        User found = null;
        if (username != null) {
            for (User usr : users) {
                if (usr.getUsername().equalsIgnoreCase(username)) {
                    found = usr;
                }
            }
        }
        return found;
    }

}

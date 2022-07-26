/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import models.*;
import dataaccess.UserDB;
import java.util.List;

/**
 *
 * @author heetk
 */
public class UserService {

    public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }

    public User get(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    }

    public void insert(String email, boolean activity, String first_name, String last_name, String password) throws Exception {
        User user = new User(email, activity, first_name, last_name, password);
        RoleDB roledb = new RoleDB();
        Role role = roledb.getRole(email);
        user.setRole(role);
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }

    public void update(String email, boolean activity, String first_name, String last_name, String password, Role role) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        user.setPassword(password);
        user.setActive(activity);
        user.setFirstName(first_name);
        user.setLastName(last_name);
        
        RoleDB roledb = new RoleDB();
        role = roledb.getRole(email);
        
        user.setRole(role);
        userDB.update(user);
    }

    public void delete(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        userDB.delete(user);
    }
}
package ru.azolotuhin.calculator.Security;

import java.util.ArrayList;
import java.util.List;

public class UserList {

    private List<User> userList;

    {
        userList = new ArrayList<>();
    }

    public UserList() {

        User admin = new User("admin", "admin", new ArrayList<>());
        admin.getRulesList().add(AuthorizationConstants.Rule.All);
        userList.add(admin);

        User norm = new User("norm","norm",new ArrayList<>());
        norm.getRulesList().add(AuthorizationConstants.Rule.Calculation);
        userList.add(norm);

        User counter = new User("count","count",new ArrayList<>());
        counter.getRulesList().add(AuthorizationConstants.Rule.Calculation);
        counter.getRulesList().add(AuthorizationConstants.Rule.Orders);
        userList.add(counter);
    }

    public User getUser(String login, String password){
        for (User user:userList) {
            if (user.getName().equals(login) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public boolean checkUser(User user){

        for (User userL:userList) {
         if (user.equals(userL)){
             return true;
         }
        }
        return false;
    }
    public AuthorizationConstants.Response checkUser(String login, String password){

        for (User userL:userList) {
         if (userL.getName().equals(login)){
             if (userL.getPassword().equals(password)){
                 return AuthorizationConstants.Response.OK;
             }else {
                 return AuthorizationConstants.Response.INVALID_PASSWORD;
             }
         }
        }
        return AuthorizationConstants.Response.NO_USER;
    }
}

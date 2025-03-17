package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import model.User;

public class LeaderboardController {

    private HashMap<Integer, User> users;

//Constructors
    public LeaderboardController(){
        users = new HashMap<>();
    }

    public void addUser(User user){
        users.put(user.getUserID(), user);
    }

    public void addScore(int userID, int score){
        User user = users.get(userID);
        if (user != null){
            user.setExp(user.getExp()+ score);
        }
    }

    //Method to get top users

}

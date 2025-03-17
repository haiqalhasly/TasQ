package model;

import java.util.ArrayList;

public class User {
    private int userID;
    private String name;
    private int exp;
    private ArrayList<Task> completedTasks;

    //Constructor for initialization
    public User(int userID, int exp){
        this.userID = userID;
        this.name = name;
        this.exp = exp;
        this.completedTasks = completedTasks;
    }


    //Getters
    public int getUserID(){ return userID;}
    public String getName(){ return name;}
    public int getExp(){ return exp;}
    public ArrayList<Task> getCompletedTasks(){ return completedTasks;}
    

    //Setters
    public void setUserID(int userID) { this.userID = userID; }
    public void setName(String name){this.name = name;}
    public void setCompletedTasks(ArrayList<Task> completedTasks){this.completedTasks = completedTasks;}
    public void setExp(int exp){this.exp = exp;}

    User user1 = new User(1, 10);
    User user2 = new User(2,20);
    User user3 = new User(3,30);
    User user4 = new User(4,40);
    User user5 = new User(5,50);

}


package model;

import java.util.ArrayList;

public class User {
    private int userID;
    private String name;
    private int exp;
    private ArrayList<Task> completedTasks;

    //Constructor for initialization
    public User(String name, int exp){
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



}


package model;

import java.util.ArrayList;

public class User {
    private int userID;
    private String name;
    private int exp;
    private ArrayList<Task> completedTasks;

    // Constructor for initialization
    public User(int userID, String name, int exp) {
        this.userID = userID;
        this.name = name;
        this.exp = exp;
        this.completedTasks = new ArrayList<>(); // Properly initialized
    }

    // Getters
    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public int getExp() {
        return exp;
    }

    public ArrayList<Task> getCompletedTasks() {
        return completedTasks;
    }

    // Setters
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setCompletedTasks(ArrayList<Task> completedTasks) {
        this.completedTasks = completedTasks;
    }

    // Method to add a completed task
    public void addCompletedTask(Task task) {
        completedTasks.add(task);
    }

    public void addExp(int exp) {
        this.exp += exp;
    }

    // Sample users
    public static ArrayList<User> createSampleUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "Alice", 0));
        users.add(new User(2, "Bob", 45));
        users.add(new User(3, "Charlie", 70));
        users.add(new User(4, "David", 100));
        return users;
    }
}

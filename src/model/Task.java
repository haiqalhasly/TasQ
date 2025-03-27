package model;
//One class

public class Task {
    private int taskID;
    private String title;
    private String description;
    private int exp;
    public boolean getTaskID;

    //Constructor for initialization
    public Task (int taskID, String title, String description, int exp) {
        this.taskID = taskID;
        this.title = title;
        this.description = description;
        this.exp = exp;

    }

    //Getters
    public int getTaskID(){ return taskID;}
    public String getTitle(){ return title;}
    public String getDescription(){ return description;}
    public int getExp(){ return exp;}

    //Setters
    public void setTitle(String title){this.title = title;}
    public void setDescription(String description){this.description = description;}
    public void setExp(int exp){this.exp = exp;}

    // Display task details (for testing)
    public void printTask() {
        System.out.println("Task ID: " + taskID);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("EXP: " + exp);
    }

    //Overrride to string method to avoid hex code

    @Override
    public String toString(){
        return title;
        
    }

}

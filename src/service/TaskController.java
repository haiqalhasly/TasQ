package service;

import java.util.ArrayList;

import model.Task;
import model.User;
//One class

public class TaskController {

    // Static instance of TaskController
    private static TaskController instance;

    private ArrayList<Task> tasks = new ArrayList<>();
    private ArrayList<User> users;

    public TaskController() {
        // if (tasks.isEmpty()) {
        //     Task task1 = new Task(1, "Initial Task 1", "Description 1", 10);
        //     tasks.add(task1);
        //     Task task2 = new Task(2, "Initial Task 2", "Description 2", 20);
        //     tasks.add(task2);
        // }

    }

    // Public method to get the single instance
    public static TaskController getInstance() {
        if (instance == null) {
            instance = new TaskController();
        }
        return instance;
    }


    public void addTask(String title, String description, int exp){

        int taskID = tasks.size()+1;
        Task newTask = new Task(taskID,title,description, exp);
        tasks.add(newTask);
        System.out.println("Task added: "+ title);


    }
    public class InnerTaskController {
        public void transferTask(ArrayList<Task> receivedTasks) {
            // Check if the list is not null
            if (receivedTasks != null) {
                // Process the received list
                System.out.println("Received Tasks:");
                for (Task tasks : receivedTasks) {
                    System.out.println("- " + tasks.toString());
                }
            }
        }
        
    }
    

    
    public void updateTask (int taskID, String title, String description, int exp){
        for (int i = 0; i<tasks.size(); i++){
            Task task = tasks.get(i);

            if(task.getTaskID() == taskID){
                task.setTitle(title);
                task.setDescription(description);
                task.setExp(exp);

                System.out.println("Task updated: "+ title);
                return;
            }

        }
        System.out.println("Task not found");

    }


    public void deleteTask (int taskID){

        for(int i=0; i<tasks.size(); i++){
            Task task = tasks.get(i);

            if (task.getTaskID() == taskID){
                tasks.remove(i);
                System.out.println("Deleted: "+ taskID);
                return;
            }
        }
        System.out.println("Tasks not found");
    }

     public void completeTask(int userId, int taskId) {
        Task completedTask = null;
        for (Task task : tasks) {
            if (task.getTaskID() == taskId) {
                completedTask = task;
                break;
            }
        }

        if (completedTask == null) {
            System.out.println("Task not found!");
            return;
        }

        for (User user : users) {
            if (user.getUserID() == userId) {
                user.setExp(user.getExp() + completedTask.getExp()); // Update user's EXP
                System.out.println(user.getName() + " completed " + completedTask.getTitle() + " and gained " + completedTask.getExp() + " EXP!");
                return;
            }
        }
        System.out.println("User not found!");
    }


     // Get All Tasks
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }



}

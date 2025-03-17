package service;

import java.util.ArrayList;
import java.util.List;

import model.Task;

public class TaskController {

    private ArrayList <Task> tasks = new ArrayList<>();

    public void addTask(String title, String description, int exp){

        int taskID = tasks.size()+1;
        Task newTask = new Task(taskID,title,description, exp);
        tasks.add(newTask);
        System.out.println("Task added: "+ title);


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


     // Get All Tasks
    public List<Task> getAllTasks() {
        return tasks;
    }

}

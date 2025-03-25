import java.util.ArrayList;
import model.Task;
import service.TaskController;

public class testTaskController {
    public void transferTask(ArrayList<Task> receivedTasks){
        // Check if the list is not null
        if (receivedTasks != null) {
            // Process the received list
            System.out.println("Received Tasks:");
            for (Task tasks : receivedTasks) {
                System.out.println("- " + tasks);
            }
        }
    }

    public static void main(String[] args) {
        // Create an instance of SourceDataManager
        TaskController sourceManager = new TaskController();

        // Create an instance of DestinationDataProcessor
        testTaskController destinationProcessor = new testTaskController();

        // Transfer the ArrayList by passing it as a parameter
        ArrayList<Task> tasks = sourceManager.getAllTasks();
        destinationProcessor.transferTask(tasks);  
    }
}

                


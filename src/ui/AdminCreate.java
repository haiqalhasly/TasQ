package ui;

import service.TaskController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Define an interface for Admin Task Management
interface AdminTaskManager {
    void addTask(ActionEvent e);

    void deleteTask(ActionEvent e);

    void updateTaskList();
}

//Implements interface AdminTaskManager. ensure the function will be used and not left out

public class AdminCreate implements AdminTaskManager{

    //Instance Fields
    private TaskController taskController;
    private DefaultListModel<String> taskListModel = new DefaultListModel<>();
    private JList<String> taskList = new JList<>(taskListModel);
    private JLabel titleLabel = new JLabel("Title", SwingConstants.CENTER);
    private JLabel descriptionLabel = new JLabel("Description", SwingConstants.CENTER);
    private JLabel expLabel = new JLabel("EXP", SwingConstants.CENTER);
    private JTextField titleField = new JTextField(15);
    private JTextField descriptionField = new JTextField(15);
    private JTextField expField = new JTextField(5);
    private JButton addButton = new JButton("Add Task");
    private JButton deleteButton = new JButton("Delete Task");

    

    public AdminCreate(JPanel panel, TaskController taskController) {
        //Panel
        panel.setBackground(new Color(20, 20, 20)); // Dark background
        panel.setLayout(new GridLayout(6, 1, 10, 10));

        this.taskController = taskController.getInstance();
        // Set text color
        taskList.setForeground(Color.WHITE);
        taskList.setBackground(new Color(30, 30, 30));
        titleField.setBackground(new Color(40, 40, 40));
        descriptionField.setBackground(new Color(40, 40, 40));
        expField.setBackground(new Color(40, 40, 40));
        titleLabel.setForeground(Color.white);
        descriptionLabel.setForeground(Color.white);
        expLabel.setForeground(Color.white);
        titleField.setForeground(Color.white);
        descriptionField.setForeground(Color.WHITE);
        expField.setForeground(Color.WHITE);

        //Add every component
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(expLabel);
        panel.add(expField);
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Button Actions using Lambda Expressions
        addButton.addActionListener(e -> addTask(e));
        deleteButton.addActionListener(e -> deleteTask(e));
    }

    // Adding task method with exception handling
    public void addTask(ActionEvent e) {
        try {
            String title = titleField.getText().trim();
            String description = descriptionField.getText().trim();

            // Validate if fields are empty
            if (title.isEmpty() || description.isEmpty() || expField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parse EXP value
            int exp = Integer.parseInt(expField.getText().trim());

            // Validate if EXP is negative
            if (exp < 0) {
                JOptionPane.showMessageDialog(null, "EXP cannot be negative.", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Add task to TaskController
            taskController.addTask(title, description, exp);

            // Update the task list
            updateTaskList();

            // Clear input fields
            titleField.setText("");
            descriptionField.setText("");
            expField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "EXP must be a valid number.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Delete task method
    public void deleteTask(ActionEvent e) {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0) {
            taskController.deleteTask(selectedIndex + 1);
            updateTaskList();
        }
    }
    //Update Task
    public void updateTaskList() {
        taskListModel.clear();
        for (model.Task task : taskController.getAllTasks()) {
            taskListModel.addElement(task.getTitle() + " - " + task.getExp() + " EXP");
        }
    }

}
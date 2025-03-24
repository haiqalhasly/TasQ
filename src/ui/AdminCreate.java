package ui;

import service.TaskController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminCreate {
    private TaskController taskController = new TaskController();
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

    public AdminCreate(JPanel panel) {
        panel.setBackground(new Color(20, 20, 20)); // Dark background
        panel.setLayout(new GridLayout(6, 1, 10, 10));

        // Set text color
        taskList.setForeground(Color.WHITE);
        taskList.setBackground(new Color(30, 30, 30));
        titleField.setBackground(new Color(40, 40, 40));
        descriptionField.setBackground(new Color(40, 40, 40));
        expField.setBackground(new Color(40, 40, 40));
        titleLabel.setForeground(Color.white);
        descriptionLabel.setForeground(Color.white);
        expLabel.setForeground(Color.white);
        expField.setForeground(Color.white);
        descriptionField.setForeground(Color.WHITE);
        expField.setForeground(Color.WHITE);

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(expLabel);
        panel.add(expField);
        panel.add(addButton);
        panel.add(deleteButton);

        panel.add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Button Actions
        addButton.addActionListener(this::addTask);
        deleteButton.addActionListener(this::deleteTask);
    }

    private void addTask(ActionEvent e) {
        String title = titleField.getText();
        String description = descriptionField.getText();
        int exp = Integer.parseInt(expField.getText());
        taskController.addTask(title, description, exp);
        updateTaskList();
    }

    private void deleteTask(ActionEvent e) {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex >= 0) {
            taskController.deleteTask(selectedIndex + 1);
            updateTaskList();
        }
    }

    private void updateTaskList() {
        taskListModel.clear();
        for (model.Task task : taskController.getAllTasks()) {
            taskListModel.addElement(task.getTitle() + " - " + task.getExp() + " EXP");
        }
    }

}
package ui;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.*;
import service.TaskController;
import service.TaskController.InnerTaskController;

public class UserUI extends JFrame {


    //Instance fields for UI

    private JPanel mainPanel, taskPanel, leaderboardPanel;
    private JLabel expLabel;
    private JButton switchToLeaderboard, switchToTasks;
    
    //Instance fields for backend
    TaskController taskController = TaskController.getInstance();
    InnerTaskController innerTaskController;
    private ArrayList<User> users;
    private User currentUser;
    private ArrayList<Task> tasks;
    private Leaderboard leaderboard;




    public UserUI() {
        super("TASQ");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setVisible(true);
        
        this.taskController = taskController;
        this.tasks = taskController.getAllTasks();
        this.innerTaskController = taskController.new InnerTaskController();
        JButton logoutButton = new JButton("Logout");

        logoutButton.addActionListener(e -> {
            LoginFrame loginFrame = new LoginFrame();
            this.dispose();
        });

        // Apply Dark Theme
        getContentPane().setBackground(new Color(40, 44, 52));
        
        

        
        users = new ArrayList<>();
        users = User.createSampleUsers();
        currentUser = users.get(0); // Example: Set Alice as the default user

        tasks = taskController.getAllTasks();

        innerTaskController.transferTask(tasks);

        leaderboard = new Leaderboard(users);

        expLabel = new JLabel("User: " + currentUser.getName() + " | EXP: " + currentUser.getExp());
        expLabel.setFont(new Font("Arial", Font.BOLD, 16));
        expLabel.setForeground(Color.WHITE);
        expLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(expLabel, BorderLayout.NORTH);

        mainPanel = new JPanel(new CardLayout());
        mainPanel.setBackground(new Color(40, 44, 52));

        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskPanel.setBackground(new Color(40, 44, 52));
        JScrollPane taskScroll = new JScrollPane(taskPanel);
        taskScroll.setBorder(null);

        leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
        leaderboardPanel.setBackground(new Color(40, 44, 52));
        JScrollPane leaderboardScroll = new JScrollPane(leaderboardPanel);
        leaderboardScroll.setBorder(null);

        mainPanel.add(taskScroll, "Tasks");
        mainPanel.add(leaderboardScroll, "Leaderboard");

        switchToLeaderboard = createStyledButton("View Leaderboard");
        switchToTasks = createStyledButton("View Tasks");

        switchToLeaderboard.addActionListener(e -> switchView("Leaderboard"));
        switchToTasks.addActionListener(e -> switchView("Tasks"));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(30, 34, 40));
        bottomPanel.add(switchToLeaderboard);
        bottomPanel.add(switchToTasks);
        bottomPanel.add(logoutButton);

        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);


        refreshTaskList();
    }

    //Styled button

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 80, 100));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(90, 100, 120), 2));

        return button;
    }

    //Card to switch view

    private void switchView(String viewName) {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, viewName);

        if (viewName.equals("Leaderboard")) {
            leaderboard.refreshLeaderboard(leaderboardPanel);
        } else {
            refreshTaskList();
        }
    }

    private void refreshTaskList() {
        taskPanel.removeAll();
        for (Task task : tasks) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(new Color(50, 54, 62));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel taskLabel = new JLabel(task.getTitle() + "  (+ " + task.getExp() + " EXP)");
            taskLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            taskLabel.setForeground(Color.WHITE);

            JButton completeButton = createStyledButton(" [/] Complete");

            completeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    completeTask(task);
                }
            });

            panel.add(taskLabel, BorderLayout.WEST);
            panel.add(completeButton, BorderLayout.EAST);
            taskPanel.add(panel);
        }
        taskPanel.revalidate();
        taskPanel.repaint();
    }

    //Complete Task MessageDialog

    private void completeTask(Task task) {
        tasks.remove(task);
        currentUser.setExp(task.getExp());
        expLabel.setText("User: " + currentUser.getName() + " | EXP: " + currentUser.getExp());
        refreshTaskList();
        JOptionPane.showMessageDialog(this, " Task Completed!\nYou gained " + task.getExp() + " EXP.",
                "Task Completed", JOptionPane.INFORMATION_MESSAGE);
    }
}

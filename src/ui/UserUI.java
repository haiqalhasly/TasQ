package ui;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.*;

public class UserUI extends JFrame {

    private JPanel mainPanel, taskPanel, leaderboardPanel;
    private JLabel expLabel;
    private JButton switchToLeaderboard, switchToTasks;
    
    private ArrayList<User> users;
    private User currentUser;
    private ArrayList<Task> tasks;
    private Leaderboard leaderboard;

    public UserUI() {
        super("To-Do List App");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setVisible(true);

        JButton logoutButton = new JButton("Logout");

        logoutButton.addActionListener(e -> {
            LoginFrame loginFrame = new LoginFrame();
            this.dispose();
        });

        // Apply Dark Theme
        getContentPane().setBackground(new Color(40, 44, 52));
        
        

        
        users = new ArrayList<>();
        users.add(new User("Alice",0));
        users.add(new User("Bob",45));
        users.add(new User("Charlie",70));
        currentUser = users.get(0);

        tasks = new ArrayList<>();
        tasks.add(new Task(1,"Homework","JAVA", 50));
        tasks.add(new Task(2,"Wash the dishes","20 dishes", 20));
        tasks.add(new Task(3,"Read Atomic Habits","Read 30 minutes", 30));
        tasks.add(new Task(4,"Exercise for 15 minutes","Push Day", 25));

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

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 80, 100));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(90, 100, 120), 2));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(90, 100, 120));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 80, 100));
            }
        });

        return button;
    }

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

            JLabel taskLabel = new JLabel("📌 " + task.getTitle() + "  (+ " + task.getExp() + " EXP)");
            taskLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            taskLabel.setForeground(Color.WHITE);

            JButton completeButton = createStyledButton("✅ Complete");

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

    private void completeTask(Task task) {
        tasks.remove(task);
        currentUser.setExp(task.getExp());
        expLabel.setText("User: " + currentUser.getName() + " | EXP: " + currentUser.getExp());
        refreshTaskList();
        JOptionPane.showMessageDialog(this, "🎉 Task Completed!\nYou gained " + task.getExp() + " EXP.",
                "Task Completed", JOptionPane.INFORMATION_MESSAGE);
    }



    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> new UserUI().setVisible(true));
    // }
}

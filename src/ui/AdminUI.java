package ui;

import javax.swing.*;

import model.User;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import service.TaskController;

//Using interface which is class from ItemListener
public class AdminUI implements ItemListener {

    private JFrame frame;
    private JPanel cardPanel;
    private TaskController taskController;
    private JPanel leaderboardPanel;
    private User currentUser;

    // Names for our cards. Cards will be use to switch between frame or panel
    private final String CRUD_CARD = "EDIT TASK";
    private final String LEADERBOARD_CARD = "LEADERBOARD";

    public AdminUI() {
        // Create a single TaskController instance
        TaskController.getInstance();

        //Frame UI
        frame = new JFrame("TasQ - Admin Panel");
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //LeaderboardPanel added here
        leaderboardPanel = new JPanel();
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
        leaderboardPanel.setBackground(new Color(40, 44, 52));
        JScrollPane leaderboardScroll = new JScrollPane(leaderboardPanel);
        leaderboardScroll.setBorder(null);

        //Make Users ArrayList as sample

        ArrayList<User> users = new ArrayList<>();
        users = User.createSampleUsers();
        currentUser = users.get(0); // Example: Set Alice as the default user

        Leaderboard leaderboard = new Leaderboard(users);
        leaderboard.refreshLeaderboard(leaderboardPanel);

        // Navigation control
        JPanel controlPanel = new JPanel();
        JButton crudButton = new JButton("Edit Task");
        JButton leaderboardButton = new JButton("Leaderboard");
        JButton logoutButton = new JButton("Logout");

        //Implements ActionListener by using Lambda Expression  
        logoutButton.addActionListener(event -> {
            new LoginFrame();
            frame.dispose();
        });

        //Add everybutton in control panel
        controlPanel.add(crudButton);
        controlPanel.add(leaderboardButton);
        controlPanel.add(logoutButton);

        // Panel with CardLayout
        cardPanel = new JPanel();
        cardPanel.setLayout(new CardLayout());

        // Create crud card UI
        JPanel crudPanel = new JPanel();

        // Pass taskController to AdminCreate
        new AdminCreate(crudPanel, taskController);

        // Create leaderboard card UI
        cardPanel.add(leaderboardScroll, LEADERBOARD_CARD);

        // Add crud and leaderboard card to main cardPanel
        cardPanel.add(crudPanel, CRUD_CARD);
        cardPanel.add(leaderboardPanel, LEADERBOARD_CARD);

        // add action listener TO CRUD CARD using Anonymous Inner Class. new operator will create the class and the instance together
        crudButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show((cardPanel), CRUD_CARD);
            }
        });

        // add action listener TO LEADERBOARD CARD using Anonymous Inner Class. new
        // operator will create the class and the instance together
        leaderboardButton.addActionListener(e -> {
            leaderboard.refreshLeaderboard(leaderboardPanel); // Refresh when switching
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, LEADERBOARD_CARD);
        });

        

        // Add panels to the frame
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(cardPanel, BorderLayout.CENTER);

        // Display the window
        frame.setVisible(true);
    }

    

    public void itemStateChanged(ItemEvent event) {
        CardLayout cardLayout = (CardLayout) (cardPanel.getLayout());
        cardLayout.show(cardPanel, (String) event.getItem());
    }
}
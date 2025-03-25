package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import service.TaskController;

//Using interface which is class from ItemListener
public class AdminUI implements ItemListener {

    private JFrame frame;
    private JPanel cardPanel;
    private TaskController taskController;

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
        JPanel leaderboardPanel = new JPanel();
        new Leaderboard(null);

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
        leaderboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show((cardPanel), LEADERBOARD_CARD);
            }
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
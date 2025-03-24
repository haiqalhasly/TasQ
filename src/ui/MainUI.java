package ui;

import javax.swing.*;
import ui.*;


import java.awt.*;
import java.awt.event.*;

public class MainUI implements ItemListener {

    private JFrame frame;
    private JPanel cardPanel;

    // Names for our cards
    private final String CRUD_CARD = "EDIT TASK";
    private final String LEADERBOARD_CARD = "LEADERBOARD";

    public MainUI() {

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

        logoutButton.addActionListener(e -> {
               LoginFrame loginFrame = new LoginFrame();
               frame.dispose();
        });

        controlPanel.add(crudButton);
        controlPanel.add(leaderboardButton);
        controlPanel.add(logoutButton);

        // Panel with CardLayout
        cardPanel = new JPanel();
        cardPanel.setLayout(new CardLayout());

        // Create crud card UI
        JPanel crudPanel = new JPanel();
        new AdminUI(crudPanel);

        // Create leaderboard card UI
        JPanel leaderboardPanel = new JPanel();
        new AdminLeaderboard();

        // Add crud and leaderboard card to main cardPanel
        cardPanel.add(crudPanel, CRUD_CARD);
        cardPanel.add(leaderboardPanel, LEADERBOARD_CARD);

        // add action listener TO CRUD CARD
        crudButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show((cardPanel), CRUD_CARD);
            }
        });

        // add action listener TO LEADERBOARD CARD
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

    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        cl.show(cardPanel, (String) evt.getItem());
    }

}

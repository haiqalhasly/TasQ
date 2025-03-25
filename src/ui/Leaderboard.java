package ui;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import model.User;

public class Leaderboard {
    private ArrayList<User> users;

    public Leaderboard(ArrayList<User> users) {
        this.users = users;
    }

    public void refreshLeaderboard(JPanel leaderboardPanel) {
        leaderboardPanel.removeAll();
        users.sort(Comparator.comparingInt(User::getExp).reversed());

        for (User user : users) {
            JLabel label = new JLabel(user.getName() + " - " + user.getExp() + " EXP");
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setForeground(Color.GRAY);
            leaderboardPanel.add(label);
        }

        leaderboardPanel.revalidate();
        leaderboardPanel.repaint();
    }
}




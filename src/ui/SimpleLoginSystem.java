package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleLoginSystem {
    // public static void main(String[] args) {
        // SwingUtilities.invokeLater(LoginFrame::new);
    // }
    LoginFrame loginFrame = new LoginFrame();
}

class LoginFrame extends JFrame {
    private JTextField idField;

    public LoginFrame() {
        setTitle("Login Page");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        add(new JLabel("Enter ID (1-Admin, 2-User):"));
        idField = new JTextField(10);
        add(idField);

        JButton loginButton = new JButton("Login");
        add(loginButton);

        loginButton.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {
        String id = idField.getText();
        if (id.equals("1")) {
            new AdminFrame(this);
            setVisible(false);
        } else if (id.equals("2")) {
            new UserFrame(this);
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class AdminFrame extends JFrame {
    public AdminFrame(JFrame loginFrame) {

        MainUI MainUI = new MainUI();


        
        
        // setTitle("Admin Page");
        // setSize(400, 200);
        // setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // setLocationRelativeTo(null);
        // setLayout(new FlowLayout());

        // add(new JLabel("Admin Page - Do something here"));

        // JButton logoutButton = new JButton("Logout");
        // add(logoutButton);

        // logoutButton.addActionListener(e -> {
        //     loginFrame.setVisible(true);
        //     dispose();
        // });

        // setVisible(true);
    }
}

class UserFrame extends JFrame {
    public UserFrame(JFrame loginFrame) {

        UserUI userUI = new UserUI();
        // setTitle("User Page");
        // setSize(400, 200);
        // setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // setLocationRelativeTo(null);
        // setLayout(new FlowLayout());

        // add(new JLabel("User Page - Do something here"));

        // JButton logoutButton = new JButton("Logout");
        // add(logoutButton);

        // logoutButton.addActionListener(e -> {
        //     loginFrame.setVisible(true);
        //     dispose();
        // });
        // setVisible(true);

    }
}

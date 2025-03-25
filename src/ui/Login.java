package ui;

import javax.swing.*;
import java.awt.*;

public class Login {

    LoginFrame loginFrame = new LoginFrame();
}

class LoginFrame extends JFrame {
    //Create ID field variable
    private JTextField idField;

    public LoginFrame() {

        //Basic Login Layout
        super("Login Page");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        //ID field input and Login Button

        add(new JLabel("Enter ID (1-Admin, 2-User):"));
        idField = new JTextField(10);
        add(idField);

        JButton loginButton = new JButton("Login");
        add(loginButton);

        //Add action listener to go to login function
        //Using lambda expression
        loginButton.addActionListener(e -> login());

        setVisible(true);
    }
    //Login function
    private void login() {

        //Exception handling.We use try to things that may go wrong. Catch to catch any error
        try {
        String id = idField.getText();
        
            switch (id) {

                case "1":
                    new AdminFrame(this);
                    setVisible(false);
                    break;

                case "2":
                    new UserFrame(this);
                setVisible(false);                   
                    break;
            
                default:
                    throw new IllegalArgumentException("Invalid ID");
            }

        // IllegalArgumentException used for String error
        }   catch (IllegalArgumentException exception){
                JOptionPane.showMessageDialog(this, "Invalid ID!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


     
//We use extends JFrame so that we do not have to create any frame. Just use the existing class from the library (Inheritance)
class AdminFrame extends JFrame {
    public AdminFrame(JFrame loginFrame) {

        new AdminUI();
    }
}

class UserFrame extends JFrame {
    public UserFrame(JFrame loginFrame) {

        new UserUI();
    }
}

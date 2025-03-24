import javax.swing.SwingUtilities;

import ui.MainUI;
import ui.Login;

public class Main {
    public static void main(String[] args) {
        // For thread safety, create and show GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Login();
            }
        });
    }
}
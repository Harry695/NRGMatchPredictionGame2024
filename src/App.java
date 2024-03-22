import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// UNUSED
public class App {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("NRG Match Prediction Game");
        
        JPanel panel = new JPanel();

        // Create a button
        JButton button = new JButton("Click Me!");
        button.setSize(100, 80);

        // Add the label and button to the JFrame
        panel.add(button);

        // Set layout (FlowLayout is the default layout manager)
        // frame.setLayout(new FlowLayout());

        // Set size of the JFrame
        frame.setSize(500, 200);

        // Set close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);

        // Make the JFrame visible
        frame.setVisible(true);
    }

    private void gameLoop() {
        
    }
}




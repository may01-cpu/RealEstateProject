import javax.swing.JOptionPane;
public class Main {

    public static void main(String[] args) {
// Display a dialog box asking the user to input their name
String name = JOptionPane.showInputDialog("Write your name:");

// Check if the user entered a name or canceled the input
if (name != null && !name.trim().isEmpty()) {
    // Display a greeting message
    JOptionPane.showMessageDialog(null, "Welcome, " + name + "!");
} else {
    JOptionPane.showMessageDialog(null, "No name entered. Goodbye!");
}
    }
}

package menuconsole;
import java.util.Scanner;
import utils.ConsoleUtils;

public class MainMenu {
    public static void display() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            ConsoleUtils.printTitle("  MainMenu ! "); 
            System.out.println("1. Login ");
            System.out.println("2. Register as client");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    ConsoleUtils.showLoading("Login",2);
                    LoginMenu loginMenu = new LoginMenu();
                    loginMenu.login(); 
                    break;
                case 2:
                    ConsoleUtils.showLoading("Register as client",2);
                    RegisterationMenu.display(); // Direct client to registration menu
                    break;
                case 3:
                    ConsoleUtils.showLoading("Loading",2);
                    System.out.println("Thank you for using RealEstate Management System!");
                    return; // Exit the application
                default:
                    System.out.println("Invalid choice! Please try again.");
            
        }
    }
}
}


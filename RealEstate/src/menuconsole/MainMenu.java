package menuconsole;
import java.util.Scanner;

public class MainMenu {
    public static void display() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Login as Client");
            System.out.println("2. Register as Client");
            System.out.println("3. Login as Worker");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    LoginMenu loginMenu = new LoginMenu();
                    loginMenu.login(); // Direct client to login
                    break;
                case 2:
                    RegisterationMenu.display(); // Direct client to registration menu
                    break;
                case 3:
                    WorkerMenu workerMenu = new WorkerMenu();
                    workerMenu.displayMenu(); // Direct worker to worker menu
                    break;
                case 4:
                    System.out.println("Thank you for using RealEstate Management System!");
                    return; // Exit the application
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

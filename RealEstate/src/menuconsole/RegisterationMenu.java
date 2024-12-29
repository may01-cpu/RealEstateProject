package menuconsole;

import service.ClientService;
import utils.ConsoleUtils;
import java.util.Scanner;

public class RegisterationMenu {

    public static void display() {
        Scanner scanner = new Scanner(System.in);
        ClientService clientService= new ClientService();

        while (true) {
            ConsoleUtils.printTitle("Registration Menu");
            System.out.println("1. Register as Client");
            System.out.println("2. Back to Main Menu");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 : registerClient(scanner,clientService);
                         ConsoleUtils.pause("Press any key to go back to main menu...");
                    break;
                case 2 : {
                    ConsoleUtils.showLoading("Returning to Main Menu",3);
                    return;
                }
                default : System.out.println("Invalid choice! Please try again ");
                    break;
            }
        }
    }

    private static void registerClient(Scanner scanner, ClientService clientService) {
        ConsoleUtils.showLoading("Registering as a Client",3);
        System.out.println();
        clientService.addClient();

    }

}

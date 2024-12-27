package menuconsole;

import model.Client;
import model.ClientType;

import java.util.Scanner;
import java.util.regex.Pattern;

public class RegisterationMenu {

//    public static void display() {
//        Scanner scanner = new Scanner(System.in);
//        Client client= new Client();
//
//        while (true) {
//            System.out.println("Registration Menu:");
//            System.out.println("1. Register as Client");
//            System.out.println("2. Back to Main Menu");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume newline
//
//            switch (choice) {
//                case 1 : registerClient(scanner, client);
//                    break;
//                case 2 : {
//                    System.out.println("Returning to Main Menu...");
//                    return;
//                }
//                default : System.out.println("Invalid choice. Please try again.");
//                    break;
//            }
//        }
//    }
//
//    private static void registerClient(Scanner scanner, Client client) {
//        System.out.println("Registering as a Client...\n");
//
//        System.out.print("Enter your Firstname: ");
//        String firstname = scanner.nextLine();
//        System.out.print("Enter your Lastname: ");
//        String lastname = scanner.nextLine();
//
//        System.out.print("Enter your phone: ");
//        String phone = scanner.nextLine();
//
//        String email;
//        while (true) {
//            System.out.print("Enter Email: ");
//            email = scanner.nextLine();
//            if (isValidEmail(email)) break;
//            System.out.println("Invalid email format. Please try again.");
//        }
//
//        System.out.print("Enter Password: ");
//        String password = scanner.nextLine();
//
//        System.out.println("Select Client Type:");
//        System.out.println("1. Buyer");
//        System.out.println("2. Tenant");
//        System.out.println("3. Seller");
//        System.out.println("4. Landlord");
//        int typeChoice = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        ClientType type = switch (typeChoice) {
//            case 1 -> ClientType.BUYER;
//            case 2 -> ClientType.TENANT;
//            case 3 -> ClientType.SELLER;
//            case 4 -> ClientType.LANDLORD;
//            default -> {
//                System.out.println("Invalid type. Returning to Registration Menu.");
//                return;
//            }
//
//        };
//
//        // Save client using clientService
//        boolean success = ClientService.addClient(client);
//        if (success) {
//            System.out.println("Client registered successfully!");
//        } else {
//            System.out.println("Registration failed! Email may already exist.");
//        }
//    }

//    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
//
//    // Method to check if an email is valid
//    public static boolean isValidEmail(String email) {
//        return Pattern.matches(EMAIL_REGEX, email);
//    }



}

package menuconsole;

import java.util.Scanner;
import java.util.List;
import model.Client;
import model.Transaction;
import service.AppointmentService;
import service.ClientService;
import service.PropertyService;
import service.TransactionService;

public class ClientMenu {
    private PropertyService propertyService;
    private AppointmentService appointmentService;
    private ClientService clientService;
    private TransactionService transactionService;
    private Client client;

    public ClientMenu(PropertyService propertyService, AppointmentService appointmentService, ClientService clientService, TransactionService transactionService, Client client) {
        this.propertyService = propertyService;
        this.appointmentService = appointmentService;
        this.clientService = clientService;
        this.transactionService = transactionService;
        this.client = client;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        switch (client.getType()) {
            case BUYER: // locataire et acheteur
                afficherMenuAcheteur(scanner);
                break;
            case SELLER: // vendeur et bayeur
                afficherMenuVendeur(scanner);
                break;
            default:
                System.out.println("Invalid client type.");
                break;
        }
        
    }
    // Menu pour l'acheteur / locataire
    private void afficherMenuAcheteur(Scanner scanner) {
        System.out.println("\n--- Buyer/Locataire Menu ---");
        System.out.println("1. View Available Properties");
        System.out.println("2. Schedule a Property Visit");
        System.out.println("3. View My Appointments");
        System.out.println("5. Generate a Contract");
        System.out.println("6. Register a Payment");
        System.out.println("7. Modify My Information");
        System.out.println("8. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                propertyService.printProperties();
                break;
            case 2:
                System.out.println("Scheduling a property visit...");
                break;
            case 3:
                appointmentService.listAppointments();
                break;
            case 4:
                transactionService.viewTransaction(client.getId());
                break;
            case 5:
                generateContract(scanner);
                break;
            case 6:
                registerPayment(scanner);
                break;
            case 7:
                clientService.updateClient(client.getId());
                break;
            case 8:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }
    private void generateContract(Scanner scanner) {
        System.out.println("Enter Transaction ID for contract generation:");
        String transactionId = scanner.nextLine();

        List<Transaction> transactions = transactionService.recupererTransactions();
        for (Transaction transaction : transactions) {
            if (transaction.getIdTransaction().equals(transactionId)) {
                transactionService.genererContrat(transaction);
                return;
            }
        }
        System.out.println("Transaction not found.");
    }

    private void registerPayment(Scanner scanner) {
        System.out.println("Enter Transaction ID for payment:");
        String transactionId = scanner.nextLine();

        System.out.println("Enter payment amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        List<Transaction> transactions = transactionService.recupererTransactions();
        for (Transaction transaction : transactions) {
            if (transaction.getIdTransaction().equals(transactionId)) {
                transactionService.enregistrerPaiement(transaction, amount);
                return;
            }
        }
        System.out.println("Transaction not found.");
    }


    // Menu pour le vendeur / bayeur
    private void afficherMenuVendeur(Scanner scanner) {
        System.out.println("\n--- Seller/Buyer Menu ---");
        System.out.println("1. Add a property listing");
        System.out.println("2. View my listed properties");
        System.out.println("3. View My Appointments");
        System.out.println("4. View My Transactions");
        System.out.println("5. Generate a Contract");
        System.out.println("6. Register a Payment");
        System.out.println("7. Modify My Information");
        System.out.println("8. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:

                System.out.println("Adding a property listing...");
                break;
            case 2:

                System.out.println("Displaying listed properties...");
                break;
            case 3:

                manageAppointments(scanner);
                break;
            case 4:

                transactionService.viewTransaction(client.getId());
                break;
            case 5:
                generateContract(scanner);
                break;
            case 6:
                registerPayment(scanner);
                break;
            case 7:
                clientService.updateClient(client.getId());
                break;
            case 8:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }
    private void manageAppointments(Scanner scanner) {
        System.out.println("\n--- Manage My Appointments ---");
        System.out.println("1. View My Appointments");
        System.out.println("2. Cancel Appointment");
    
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            switch (choice) {
                case 1:
                    appointmentService.viewAppointmentsForClient(client.getId()); // Display client's appointments
                    break;
                case 2:
                    appointmentService.removeAppointment(scanner); // Remove specific appointment by ID
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }
}

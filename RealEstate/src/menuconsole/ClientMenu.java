package menuconsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Client;
import model.Property;
import service.AppointmentService;
import service.PropertyService;

public class ClientMenu {
    private Client client ;
    private PropertyService propertyService;
    private ArrayList<Property>properties;

    public ClientMenu(Client client, PropertyService propertyService){
        this.client=client;
        this.propertyService = propertyService;
        this.properties= new ArrayList<>();
        initializeProperties();
    }

    public void displayMenu(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\n---Client Menu---");
            System.out.println("1. View My Information");
            System.out.println("2. Search Properties");
            System.out.println("3. View Assigned Properties");
            System.out.println("4. Manage My Appointments ");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                viewMyInformation();
                break;
                case 2:
                searchProperties(scanner);
                break;
                case 3:
                viewAssignedProperties();
                break;
                case 4:
                manageAppointments(scanner);
                    return;
                case 5:    
                System.out.println("Exiting");
                    return;
                default:
                System.out.println("Invalid choice.");
                break;
            }
        }
    }
    private void viewMyInformation(){
        System.out.println("\n--- My Inormation ---");
        System.out.println("Name: " +client.getFirstName()+ " " + client.getLastName());
        System.out.println("Email: "+client.getEmail());
        System.out.println("Phone Number : " +client.getPhoneNumber());
        System.out.println("Client Type: " +client.getType());
    }
    private void searchProperties(Scanner scanner){
        System.out.println("Select search critere:");
        System.out.println("1. Location ");
        System.out.println("2. Price");
        System.out.println("3. Type");
        System.out.print("Enter your choice (1-3): ");
        int choice = scanner.nextInt();

        String criteria ="";
        switch (choice){
        case 1 :
            System.out.print("Enter location: ");
            criteria = scanner.nextLine();
            break;
        case 2 :
            System.out.print("Enter maximum price: ");
            criteria= scanner.nextLine();
            break;
        case 3:
            System.out.print("Enter property type (e.g., Apartment, House): ");
            criteria = scanner.nextLine();
            break;
        default:
            System.out.println("Invalid choice.");
            return;
          }
          System.out.println("\n--- Search Results---");
          boolean found = false;
          for (Property property : propertyService.getProperties()) { 
            if (matchesCriteria(property, choice, criteria)) {
                System.out.println(property); 
                found = true;
            }
        }
        if (!found) {
            System.out.println("No properties found matching the criteria.");
        }
    }

    private boolean matchesCriteria(Property property, int choice, String criteria) {
        switch (choice) {
            case 1: 
                return property.getLocation().toString().toLowerCase().contains(criteria.toLowerCase());
            case 2: 
                return property.getPrice() <= Double.parseDouble(criteria);
            case 3: 
                return property.getType().toString().equalsIgnoreCase(criteria);
            default:
                return false;
        }
    }

    private void viewAssignedProperties() {
        System.out.println("\n--- Assigned Properties ---");
        if (properties.isEmpty()) {
            System.out.println("No properties assigned to you.");
        } else {
            for (Property property : properties) {
                System.out.println(property); 
            }
        }
    }

    private void initializeProperties() {
    List<Property> allProperties = propertyService.getProperties();

    
    for (Property property : allProperties) {
        if (property.getAssignedClientId().equals(client.getId())) { 
            properties.add(property); 
        }
    }
}

//methode for manageAppointments
private void manageAppointments(Scanner scanner) {
    AppointmentService appointmentService = new AppointmentService();

    System.out.println("\n--- Manage My Appointments ---");
    System.out.println("1. View My Appointments");
    System.out.println("2. Cancel Appointment");

    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    
    switch (choice) {
        case 1:
            appointmentService.viewAppointmentsForClient(client.getId()); // Display client's appointments
            break;
        case 2:
            appointmentService.removeAppointment( scanner); // Remove specific appointment by ID
            break;
        default:
            System.out.println("Invalid choice.");
            break;
    }
}
}
    

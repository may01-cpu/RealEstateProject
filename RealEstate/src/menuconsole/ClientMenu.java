package menuconsole;

import java.util.ArrayList;
import java.util.Scanner;

import model.Client;
import model.Property;

public class ClientMenu {
    private Client client ;
    private ArrayList<Property>properties;

    public ClientMenu(Client client){
        this.client=client;
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
            System.out.println("4. Exit");

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
                System.out.println("Exiting...");
                return;
                default:
                System.out.println("Invalid choice! Try again.");
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
            criteria = scanner.nextLine();
            break;
        case 3:
            System.out.print("Enter property type (e.g., Apartment, House): ");
            criteria = scanner.nextLine();
            break;
        default:
            System.out.println("Invalid choice! Returning to menu.");
            return;
          }
          System.out.println("\n--- Search Results---");
          boolean found = false;
          
     


        }
    }



}

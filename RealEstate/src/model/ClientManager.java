package model;
import java.util.ArrayList;
import model.Client;

public class ClientManager {
    private ArrayList<Client> clients = new ArrayList<>();

    // Method to add a client
    public void addClient(Client client) {
        clients.add(client);
    }

    // Method to check if a user exists and add them as a client (e.g., after login)
    public void addClientAfterLogin(String firstName, String lastName, String psw, String phoneNum, String email, String ID, UserType userType, ClientType clientType) {
        Client newClient = new Client(firstName, lastName, psw, phoneNum, email, ID, userType, clientType);
        addClient(newClient);  // Add the client to the list
    }

    // Display all clients
    public void displayClients() {
        for (Client client : clients) {
            System.out.println(client.getFirstName() + " " + client.getLastName() + " - " + client.getType());
        }
    }
}

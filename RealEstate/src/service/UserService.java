package service;

import model.Client;
import model.User;
import model.UserType;
import java.util.ArrayList;

public class UserService {
    private ArrayList<User> users; // This will store all users (clients and workers)

    public UserService() {
        users = new ArrayList<>();
    }

    // Add a user (client or worker)
    public void addUser(User user) {
        users.add(user);
        System.out.println("User added successfully!");
    }

    // Find a user by ID and check credentials (for login)
    public User login(String id, String password) {
        for (User user : users) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                return user; // Return the user if credentials match
            }
        }
        return null; // Return null if no match found
    }

    // Method to get all clients (for displaying in the worker menu)
    public ArrayList<Client> getClients() {
        ArrayList<Client> clients = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Client) { // Only add clients
                clients.add((Client) user);
            }
        }
        return clients;
    }
}

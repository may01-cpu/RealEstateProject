package service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import model.Appointment;
import model.AppointmentState;
import model.Client;
import model.Worker;



public class AppointmentService {
    private  ArrayList<Appointment> AppList = new ArrayList<>();
    private final String APPOINTMENT_FILE = "appointment.csv";  // File name to store appointments
    public AppointmentService() {
        // Initialize anything if needed
    }
    
    public void createAppointment(Scanner scanner, ClientService clientService, Worker createdBy) {
        System.out.println("\n--- Create Appointment ---");
    
        // Read and parse date and time
        System.out.print("Enter appointment date and time (yyyy-MM-dd HH:mm): ");
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            System.out.println("Error: Invalid date and time format.");
            return;
        }
    
        if (dateTime.isBefore(LocalDateTime.now())) {
            System.out.println("Error: The appointment date must be in the future.");
            return;
        }
    
        
        System.out.print("Enter client ID: ");
        String clientID = scanner.nextLine();

        // Validate if the client ID exists using ClientService
        List<Client> clients = clientService.loadClientsFromFile();
        boolean clientExists = clients.stream().anyMatch(client -> client.getId().equals(clientID));

        if (!clientExists) {
        System.out.println("Error: Client ID does not exist.");
        return;
         }
    
    
        Appointment newAppointment = new Appointment(dateTime, AppointmentState.SCHEDULED, createdBy, clientID);
    
        if (AppList.stream().anyMatch(app -> app.getIdAppointment().equals(newAppointment.getIdAppointment()))) {
            System.out.println("Error: Appointment with this ID already exists.");
            return;
        }
    
        AppList.add(newAppointment);
        System.out.println("Appointment created successfully: " + newAppointment);
    }
    

    // Méthode pour lister tous les rendez-vous
    public void listAppointments() {
        System.out.println("\n--- Appointment List ---");

        if (AppList.isEmpty()) {
            System.out.println("No appointments to display.");
            return;
        }

        for (Appointment appointment : AppList) {
            System.out.println(appointment);
        }
        
    }
    // Save appointments to a file
    public void saveAppointmentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENT_FILE))) {
            for (Appointment appointment : AppList) {
                String line = String.join(",",
                    appointment.getIdAppointment(),
                    appointment.getDateTime().toString(),
                    appointment.getState().name(),
                    appointment.getCreatedAt().toString(),
                    appointment.getCreatedBy().getId(),
                    appointment.getClientId()
                );
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Appointments saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving appointments to file: " + e.getMessage());
        }
    }
    
    
    // Remove an appointment by ID
    public void removeAppointment(Scanner scanner) {
        System.out.println("\n--- Remove Appointment ---");
        System.out.print("Enter appointment ID to remove: ");
        String appointmentId = scanner.nextLine();

        Appointment appointmentToRemove = null;
        for (Appointment appointment : AppList) {
            if (appointment.getIdAppointment().equals(appointmentId)) {
                appointmentToRemove = appointment;
                break;
            }
        }

        if (appointmentToRemove != null) {
            AppList.remove(appointmentToRemove);
            System.out.println("Appointment removed successfully.");
        } else {
            System.out.println("Error: Appointment with this ID not found.");
        }
    }
    // Update the state of an appointment by ID
    public void updateAppointmentState(Scanner scanner) {
        System.out.println("\n--- Update Appointment State ---");

        System.out.print("Enter appointment ID to update: ");
        String appointmentId = scanner.nextLine();

        Appointment appointmentToUpdate = null;

        // Find the appointment with the given ID
        for (Appointment appointment : AppList) {
            if (appointment.getIdAppointment().equals(appointmentId)) {
                appointmentToUpdate = appointment;
                break;
            }
        }

        if (appointmentToUpdate != null) {
            System.out.println("Current state: " + appointmentToUpdate.getState());
            System.out.println("Select new state from the following options:");

            // Dynamically display all enum values
            for (AppointmentState state : AppointmentState.values()) {
                System.out.println("- " + state.name());
            }
            System.out.print("Enter the new state: ");
            String newStateInput = scanner.nextLine().toUpperCase();

            try {
                AppointmentState newState = AppointmentState.valueOf(newStateInput);

                if (appointmentToUpdate.getState() == newState) {
                    System.out.println("The appointment is already in this state.");
                    return;
                }

                appointmentToUpdate.setState(newState);
                System.out.println("Appointment state updated successfully to: " + newState);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Invalid state entered.");
            }
        } else {
            System.out.println("Error: Appointment with this ID not found.");
        }
    }
    // Method to view appointments for a specific client

    public void viewAppointmentsForClient(String clientId) {
        // Filter appointments for the given clientId
        List<Appointment> clientAppointments = AppList.stream()
                .filter(appointment -> appointment.getClientId().equals(clientId))
                .collect(Collectors.toList());

        if (clientAppointments.isEmpty()) {
            System.out.println("No appointments found for client with ID: " + clientId);
        } else {
            System.out.println("Appointments for client ID: " + clientId);
            for (Appointment appointment : clientAppointments) {
                System.out.println(appointment);  // Print appointment details
            }
        }
        }



 }

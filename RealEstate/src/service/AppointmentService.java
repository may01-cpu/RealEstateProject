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
import model.Worker;


public class AppointmentService {
    private  ArrayList<Appointment> AppList = new ArrayList<>(100);
    private final String APPOINTMENT_FILE = "appointments.dat";  // File name to store appointments
    public AppointmentService() {
        // Initialize anything if needed
    }


    // Méthode pour créer un rendez-vous
    public void createAppointment(Scanner scanner, Worker createdBy) {

        System.out.println("\n--- Create Appointment ---");
    // to read the datetime
        System.out.print("Enter appointment date and time (yyyy-MM-dd HH:mm): ");
        String dateTimeInput = scanner.nextLine();
        LocalDateTime dateTime;
    //verification
        try {
            dateTime = LocalDateTime.parse(dateTimeInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            System.out.println("Error: Invalid date and time format.");
            return;
        }
        if (dateTime.isBefore(LocalDateTime.now())) {
            System.out.println("Error: The appointment date must be in the future.");
            return;
        }  

        // the appointment state
        AppointmentState state = AppointmentState.SCHEDULED;
        // clientID
         System.out.println("ID of the client");
         String clientID = scanner.nextLine();
         
        Appointment newAppointment = new Appointment(dateTime, state, createdBy,clientID);
        // Vérifie si l'ID est unique
        for (Appointment appointment : AppList) {
            if (appointment.getIdAppointment().equals(newAppointment.getIdAppointment())) {
                System.out.println("Error: Appointment with this ID already exists.");
                return;
            }
        }

        // Ajouter le rendez-vous à la liste
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
        //Approve or reject an appointment??
    }
     // Save appointments to a file
     public void saveAppointmentsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(APPOINTMENT_FILE))) {
            oos.writeObject(AppList);
            System.out.println("Appointments saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving appointments to file: " + e.getMessage());
        }
    }
    // Load appointments from a file
    @SuppressWarnings("unchecked")
    public void loadAppointmentsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(APPOINTMENT_FILE))) {
            AppList = (ArrayList<Appointment>) ois.readObject();
            System.out.println("Appointments loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading appointments: " + e.getMessage());
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


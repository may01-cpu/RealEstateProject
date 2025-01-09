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



public class AppointmentService {
    private  ArrayList<Appointment> AppList = new ArrayList<>();
    private final String APPOINTMENT_FILE = "appointment.csv";  // File name to store appointments
    public AppointmentService() {
        // Initialize anything if needed
    }


    public void createAppointment(Scanner scanner) {
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
        
        System.out.print("Enter client ID: ");
        String clientID = scanner.nextLine();
        System.out.print("enter worker name");
        String  createdBy=scanner.nextLine();
        Appointment newAppointment = new Appointment(dateTime, state, createdBy,clientID);
         // Ajouter le rendez-vous à la liste
        System.out.println("Appointment created successfully: " + newAppointment);
        saveAppointmentToFile(newAppointment);
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


    // Helper method to save an appointment to the CSV file
    private void saveAppointmentToFile(Appointment appointment) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENT_FILE, true))) {
        writer.write(appointment.getIdAppointment() + "," +
                appointment.getDateTime() + "," +
                appointment.getState() + "," +
                appointment.getCreatedAt() + "," +
                appointment.getworkerId() + "," +
                appointment.getClientId());
        writer.newLine();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    // Helper method to load all appointments from the CSV file
        public List<Appointment> loadAppointmentsFromFile() {
        List<Appointment> appointments = new ArrayList<>();
        File file = new File(APPOINTMENT_FILE);
        if (!file.exists()) {
        return appointments;  // If file doesn't exist, return an empty list
        }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 6) {
                // Assuming fields are in the format: idAppointment, dateTime, state, createdAt, workerId, clientId
                Appointment appointment = new Appointment(
                    LocalDateTime.parse(fields[1]),  // dateTime
                    AppointmentState.valueOf(fields[2]),  // state
                    LocalDateTime.parse(fields[3]),  // createdAt
                    fields[4],  // workerId
                    fields[5],  // clientId
                    fields[0]   // idAppointment
                );
                appointments.add(appointment);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return appointments;
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
            saveAppointmentToFile(appointmentToRemove);
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
                saveAppointmentToFile(appointmentToUpdate);
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

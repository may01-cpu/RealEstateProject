package service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Appointment;
import model.AppointmentState;
import model.Worker;

public class AppointmentService {
    private  ArrayList<Appointment> AppList = new ArrayList<>(100);
    private final String APPOINTMENT_FILE = "appointments.dat";  // File name to store appointments


    // Méthode pour créer un rendez-vous
    public void createAppointment(LocalDateTime dateTime, AppointmentState state, Worker createdBy) {
        // Vérification si la date est valide
        if (dateTime == null || dateTime.isBefore(LocalDateTime.now())) {
            System.out.println("Error: The appointment date must be in the future.");
            return;
        }

        Appointment newAppointment = new Appointment(dateTime, state, createdBy);

        // Vérifie si l'ID est unique
        for (Appointment appointment : AppList) {
            if (appointment.getIdAppointment().equals(newAppointment.getIdAppointment())) {
                System.out.println("Error: Appointment with this ID already exists.");
                return;
            }
        }

        // Ajouter le rendez-vous à la liste
        AppList.add(newAppointment);
        System.out.println("Rendez-vous créé avec succès : " + newAppointment);
    }

    // Méthode pour lister tous les rendez-vous
    public void listAppointments() {
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
    public void removeAppointment(String appointmentId) {
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
     public void updateAppointmentState(String appointmentId, AppointmentState newState) {
        for (Appointment appointment : AppList) {
            if (appointment.getIdAppointment().equals(appointmentId)) {
                appointment.setState(newState);
                System.out.println("Appointment state updated successfully.");
                return;
            }
        }
        System.out.println("Error: Appointment with this ID not found.");
    }
}


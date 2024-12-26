package service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Appointment;
import model.AppointmentState;
import model.Worker;

public class AppointmentService {
    private ArrayList<Appointment> AppList = new ArrayList<>(100);

    // Méthode pour créer un rendez-vous
    public void createAppointment(LocalDateTime dateTime, AppointmentState state, Worker createdBy) {
        // Vérification si la date est valide
        if (dateTime == null || dateTime.isBefore(LocalDateTime.now())) {
            System.out.println("Erreur : La date du rendez-vous doit être dans le futur !");
            return;
        }

        // Créer un nouvel objet Appointment
        Appointment newAppointment = new Appointment(dateTime, state, createdBy);

        // Vérifie si l'ID est unique
        for (Appointment appointment : AppList) {
            if (appointment.getIdAppointment().equals(newAppointment.getIdAppointment())) {
                System.out.println("Erreur : Un rendez-vous avec cet ID existe déjà !");
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
            System.out.println("Aucun rendez-vous à afficher.");
            return;
        }

        for (Appointment appointment : AppList) {
            System.out.println(appointment);
        }
        //Approve or reject an appointment??
    }
}


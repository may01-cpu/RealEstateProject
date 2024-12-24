package utils;

import java.util.UUID;

public class IDGenerator {

    // Méthode pour générer un ID unique
    public static String generateAppointmentID() {
        return "APP-" + UUID.randomUUID().toString();
    }
}


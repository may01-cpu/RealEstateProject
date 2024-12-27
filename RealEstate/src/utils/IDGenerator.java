package utils;

import java.util.UUID;

public class IDGenerator {

    // Méthode pour générer un ID unique
    public static String generateID( String entityType) {
       
         // Generate a unique UUID
         UUID uuid = UUID.randomUUID();

         // Encode the UUID to a base64 string and shorten it (first 8 characters)
         String shortID = uuid.toString().replaceAll("-", "").substring(0, 8);
 
         // Return the unique ID with entity type
         return String.format("%s-%s", entityType, shortID);
    }
}


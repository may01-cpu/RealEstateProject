package service;

import model.Property;
import model.PropertyFiltration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyService implements propertyinter {

    // IdProperty|Type|Size |Price|location|Stat|LegalStat
    private List<Property> properties = new ArrayList<>();
    private static final String FILE_PATH = "resources/property.txt";

    public PropertyService() {
        loadProperties();
    }

    // Load properties from the file
    private void loadProperties()  {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    properties.add(Property.fromFileFormat(line));
                }
                System.out.println("Properties loaded.");
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        } else {
            try {
                file.getParentFile().mkdirs(); // Ensure directory exists
                file.createNewFile(); // Create file if it doesn't exist
                System.out.println("New properties file created.");
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }

    // Save properties to the file
    private void saveProperties() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Property property : properties) {
                pw.println(property.toFileFormat());
            }
            System.out.println("Properties saved.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Print all properties
    public void printProperties() {
        if (properties.isEmpty()) {
            System.out.println("No properties to display.");
        } else {
            properties.forEach(System.out::println);
        }
    }

    @Override
    public void addProperty(Property property) {

    }

    @Override
    public void updateProperty(Property property) {

    }

    @Override
    public void deleteProperty(int propertyId) {

    }

    @Override
    public Property getPropertyDetails(int propertyId) {
        return null;
    }

    @Override
    public List<Property> searchProperties(PropertyFiltration criteria) {
        return List.of();
    }




}



package service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.*;
import utils.IDGenerator;

public class PropertyService implements propertyinter {


    private List<Property> properties = new ArrayList<>();
    private static final String FILE_PATH = "RealEstate/recources/property.txt";

    public PropertyService() {
        loadProperties();
    }


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
    private void saveProperties() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Property property : properties) {
                pw.println(property.toFileFormat());
            }

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private void  saveProperty(Property property) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            pw.println(property.toFileFormat());

        }
        catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }


    public void printProperties() {
        if (properties.isEmpty()) {
            System.out.println("No properties to display.");
        } else {
            properties.forEach(System.out::println);
        }
    }

    @Override
    public void addProperty() {

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter Property Type ( STUDIO,F2,F3,F4,F5,DUPLEX,R1,R2,R3): ");
        PropertyType type = PropertyType.valueOf(scan.nextLine().toUpperCase());

        System.out.println("Enter the size:");
        double size=scan.nextDouble();

        System.out.println("Enter the price:");
        double price=scan.nextDouble();

        System.out.println("Enter country :");
        String country= scan.nextLine();
        System.out.println("Enter wilaya :");
        String wilaya= scan.nextLine();
        System.out.println("Enter dayra :");
        String dayra= scan.nextLine();
        System.out.println("Enter street :");
        String street= scan.nextLine();


        System.out.println("Do you wanna Offer the property:");
        System.out.println("1. for Sale");
        System.out.println("2. for rent");
        int Choice = scan.nextInt();
        PropertyStat stat = switch (Choice) {
            case 1 -> PropertyStat.FOR_SALE;
            case 2 -> PropertyStat.FOR_RENT;
            default -> {
                System.out.println("Invalid state.");
                yield null;
            }
        };

        System.out.println("Enter the new Property Legal State:\n-NEW\n-UNDER_CONSTRUCTION\n-RENOVATED\n-GOOD_CONDITION\n-DILAPIDATED\n-NEEDS_REPAIRS");
        PropertyLegalStat legalstat=PropertyLegalStat.valueOf(scan.nextLine().toUpperCase());

        String id= IDGenerator.generateID("P");
        Property p=new Property(id,type,size,price,new Address(country,wilaya,dayra,street),stat,legalstat);
        properties.add(p);
        saveProperty(p);
    }

    @Override
    public void updateProperty(String propertyId) {
        Scanner scan = new Scanner(System.in);
        Property property =properties.stream().filter(p ->p.getIdProperty() .equals(propertyId)).findFirst().orElse(null);

        if (property == null) {
            System.out.println("Property not found !");
            return;
        }

        System.out.println("What would you like to update?");
        System.out.println("1.Price\n2.State\n3.Legal State\n");
        int choice = scan.nextInt();
        scan.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter the new price : ");
                double newPrice = scan.nextDouble();
                property.setPrice(newPrice);
                System.out.println("Price updated successfully!");
                break;
            case 2:
                System.out.print("Enter the new state (RENTED,FOR_SALE,SOLD,FOR_RENT) : ");
                PropertyStat stat=PropertyStat.valueOf(scan.nextLine().toUpperCase());
                property.setStat(stat);
                System.out.println("Property state updated successfully!");
                break;
            case 3:
                System.out.println("Enter the new Property Legal State:\n-NEW\n-UNDER_CONSTRUCTION\n-RENOVATED\n-GOOD_CONDITION\n-DILAPIDATED\n-NEEDS_REPAIRS");
                PropertyLegalStat legalstat=PropertyLegalStat.valueOf(scan.nextLine().toUpperCase());
                System.out.println("Property legal state updated successfully!");
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        for(int i=0;i<=properties.size();i++) {
            if(properties.get(i).getIdProperty().equals(propertyId)) {
                properties.set(i, property);
                break;
            }
        }

        saveProperties();
    }

    @Override
    public void deleteProperty(String propertyId) {
        Property ToDelete = properties.stream().filter(p -> p.getIdProperty().equals(propertyId)).findFirst().orElse(null);

        if (ToDelete == null) {
            System.out.println("Property not found.");
            return;
        }

        properties.remove(ToDelete);
        saveProperties();
        System.out.println("Property deleted successfully.");
    }


    @Override
    public void getPropertyDetails(String propertyId) {
        Property property = properties.stream().filter(p -> p.getIdProperty().equals(propertyId)).findFirst().orElse(null);
        if(property==null){
            System.out.println("Property not found");
            return;
        }
        System.out.println("Property details :");
        property.toString();
    }


    @Override
    public void searchPropertiesByFiltration() {
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter the min size:");
        double minSize=scan.nextDouble();
        System.out.println("Enter the min price:");
        double minPrice=scan.nextDouble();
        System.out.println("Enter the max price:");
        double maxPrice=scan.nextDouble();
        System.out.print("Enter Property Type ( STUDIO,F2,F3,F4,F5,DUPLEX,R1,R2,R3): ");
        PropertyType type = PropertyType.valueOf(scan.nextLine().toUpperCase());

        PropertyFiltration criteria=new PropertyFiltration(minPrice,maxPrice,minSize,type);
        criteria.printFilteredProperties(criteria.filterProperties(properties));
    }


    public Property getPropertyById(String propertyId) {
        return null;
    }
}
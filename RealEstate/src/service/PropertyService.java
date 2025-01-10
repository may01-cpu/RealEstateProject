package service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.*;
import utils.IDGenerator;

public class PropertyService implements propertyinter {


    private List<Property> properties = new ArrayList<>();
    private static final String FILE_PATH = "recources/property.txt";

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
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        }
    }
    private void saveProperties() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Property property : properties) {
                bw.write(property.toFileFormat());
                bw.newLine();
            }


        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private void  saveProperty(Property property) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH,true))) {

            bw.write(property.toFileFormat());
            bw.newLine();

        }
        catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }


    public void printProperties() {
        if (properties.isEmpty()) {
            System.out.println("No properties to display.");
        } else {
            for (Property property : properties) {
                System.out.println(property);
            }

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
        scan.nextLine();
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

        System.out.println("Enter the new Property Legal State:");
        System.out.println("1. NEW");
        System.out.println("2. UNDER_CONSTRUCTION");
        System.out.println("3.DILAPIDATED");
        System.out.println("4.NEEDS_REPAIRS");
        System.out.println("5.GOOD_CONDITION");
        System.out.println("6.RENOVATED");
        Choice = scan.nextInt();
        PropertyLegalStat legalstat = switch (Choice) {
            case 1 ->PropertyLegalStat.NEW;
            case 2 -> PropertyLegalStat.UNDER_CONSTRUCTION;
            case 3 -> PropertyLegalStat.DILAPIDATED;
            case 4 -> PropertyLegalStat.NEEDS_REPAIRS;
            case 5 -> PropertyLegalStat.GOOD_CONDITION;
            case 6 -> PropertyLegalStat.RENOVATED;

            default -> {
                System.out.println("Invalid state.");
                yield null;
            }
        };

        String id= IDGenerator.generateID("P");
        Property p=new Property(id,type,size,price,new Address(country,wilaya,dayra,street),stat,legalstat);
        properties.add(p);
        saveProperty(p);
        System.out.println("Client added succesfully");


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
                property.setStat(stat);
                System.out.println("Property state updated successfully!");
                break;
            case 3:
                System.out.println("Enter the new Property Legal State:");
                System.out.println("1. NEW");
                System.out.println("2. UNDER_CONSTRUCTION");
                System.out.println("3.DILAPIDATED");
                System.out.println("4.NEEDS_REPAIRS");
                System.out.println("5.GOOD_CONDITION");
                System.out.println("6.RENOVATED");
                int ch = scan.nextInt();
                PropertyLegalStat legalstat = switch (ch) {
                    case 1 ->PropertyLegalStat.NEW;
                    case 2 -> PropertyLegalStat.UNDER_CONSTRUCTION;
                    case 3 -> PropertyLegalStat.DILAPIDATED;
                    case 4 -> PropertyLegalStat.NEEDS_REPAIRS;
                    case 5 -> PropertyLegalStat.GOOD_CONDITION;
                    case 6 -> PropertyLegalStat.RENOVATED;

                    default -> {
                        System.out.println("Invalid state.");
                        yield null;
                    }
                };
                property.setLegalStat(legalstat);
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
        System.out.println(property);
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
        PropertyType type = PropertyType.valueOf(scan.next().toUpperCase());

        PropertyFiltration criteria=new PropertyFiltration(minPrice,maxPrice,minSize,type);
        criteria.printFilteredProperties(criteria.filterProperties(properties));
    }


    public Property getPropertyById(String propertyId) {
        return properties.stream().filter(p -> p.getIdProperty().equals(propertyId)).findFirst().orElse(null);
    }
}
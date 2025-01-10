package model;

import java.util.ArrayList;
import java.util.List;

public class PropertyFiltration {
    private final PropertyType type;
    private final double minSize;
    private final double minPrice;
    private final double maxPrice;

    public PropertyFiltration(double minPrice, double maxPrice, double minSize,PropertyType type) {

        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minSize = minSize;
        this.type = type;
    }

    public List<Property> filterProperties(List<Property> properties) {
        List<Property> filteredProperties = new ArrayList<>();

        for (Property property : properties) {

            boolean matches = minPrice == 0 || !(property.getPrice() < minPrice);

            if (maxPrice !=0 && property.getPrice() > maxPrice) {
                matches = false;
            }

            if (minSize != 0 && property.getSize() < minSize) {
                matches = false;
            }

            if (type != null && !property.getType().equals(type)) {
                matches = false;
            }

            if (matches) {
                filteredProperties.add(property);
            }
        }

        return filteredProperties;
    }

    public void printFilteredProperties(List<Property> properties){
        if (properties.isEmpty()) {
            System.out.println("No properties fit this criteria");
        } else {
            for (Property property : properties) {
                System.out.println(property);
            }
        }
    }

}

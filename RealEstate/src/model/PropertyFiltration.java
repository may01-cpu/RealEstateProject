package model;

import java.util.ArrayList;
import java.util.List;

public class PropertyFiltration {
    private PropertyType type;
    private int minSize;
    private double minPrice;
    private double maxPrice;
    private Address location;

    public PropertyFiltration(Address location, Integer minPrice, Integer maxPrice, Integer minSize,PropertyType type) {
        this.location = location;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minSize = minSize;
        this.type = type;
    }

    public List<Property> filterProperties(List<Property> properties) {
        List<Property> filteredProperties = new ArrayList<>();

        for (Property property : properties) {
            boolean matches = true;

            if (location != null && !property.getLocation().equals(location)) {
                matches = false;
            }

            if (minPrice !=0 && property.getPrice() < minPrice) {
                matches = false;
            }

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

}

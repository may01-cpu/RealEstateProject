package service;

import model.Property;
import model.PropertyFiltration;

import java.util.List;

public interface propertyinter {
    void addProperty(Property property);
    void updateProperty(Property property);
    void deleteProperty(int propertyId);
    Property getPropertyDetails(int propertyId);
    List<Property> searchProperties(PropertyFiltration criteria);
}

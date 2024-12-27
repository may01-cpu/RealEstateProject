package service;

import model.Property;
import model.PropertyFiltration;

import java.util.List;

public interface propertyinter {
    void addProperty();
    void updateProperty(String propertyId);
    void deleteProperty(String propertyId);
    Property getPropertyDetails(String propertyId);
    List<Property> searchProperties(PropertyFiltration criteria);
}

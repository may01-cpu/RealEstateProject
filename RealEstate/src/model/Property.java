package model;

public class Property {

    public record Address() {
        static  String wilaya;
        static String dayra;
        static String city;
        static String street;
    }

    private PropertyType Type;
    private double Size;
    private double Price;
    private Address location;
    private String IdProperty;
    private PropertyStat Stat;
    private PropertyLegalStat LegalStat;

    public String getIdProperty( String IdProperty) {
		return IdProperty ;
	}
}

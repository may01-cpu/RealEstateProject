package model;

public class Property {

	public record Address(String wilaya, String dayra, String city, String street) {

		public Address(String wilaya, String dayra, String city, String street) {
			this.wilaya = wilaya;
			this.dayra = dayra;
			this.city = city;
			this.street = street;
		}

		// Static method to create Address from a string
		public static Address fromString(String addressStr) {
			String[] parts = addressStr.split(",", 4);
			return new Address(
					parts.length > 0 ? parts[0].trim() : "",
					parts.length > 1 ? parts[1].trim() : "",
					parts.length > 2 ? parts[2].trim() : "",
					parts.length > 3 ? parts[3].trim() : ""
			);
		}
	}

    private PropertyType Type;
    private double Size;
    private double Price;
    private Address location;
    private final String IdProperty;
    private PropertyStat Stat;
    private PropertyLegalStat LegalStat;


    public Property(String idProperty, PropertyType type, double size, double price, Address location,
                    PropertyStat stat, PropertyLegalStat legalStat) {
        Type = type;
        Size = size;
        Price = price;
        this.location=location;
        IdProperty = idProperty;//it will change to idgenerator
        Stat = stat;
        LegalStat = legalStat;
    }

    public String getIdProperty() {
        return IdProperty;
    }

    public PropertyType getType() {
        return Type;
    }
    public void setType(PropertyType type) {
        Type = type;
    }

    public double getSize() {
        return Size;
    }
    public void setSize(double size) {
        Size = size;
    }

    public double getPrice() {
        return Price;
    }
    public void setPrice(double price) {
        Price = price;
    }

    public Address getLocation() {
        return location;
    }
    public void setLocation(Address location) {
        this.location = location;
    }

    public PropertyStat getStat() {
        return Stat;
    }
    public void setStat(PropertyStat stat) {
        Stat = stat;
    }

    public PropertyLegalStat getLegalStat() {
        return LegalStat;
    }
    public void setLegalStat(PropertyLegalStat legalStat) {
        LegalStat = legalStat;
    }

    @Override
    public String toString() {
        return "IdProperty=" + IdProperty + ",Type=" + Type + ", Size=" + Size + ", Price=" + Price + ", location=" + location
                + ", Stat=" + Stat + ", LegalStat=" + LegalStat + "]";
    }

    // Serialize to file format
    public String toFileFormat() {
        return   IdProperty + "|" + Type + "|" + Size + "|" + Price + "|" + location+ "|" + Stat + "|" + LegalStat ;
    }


    // Deserialize from file format
    public static Property fromFileFormat(String line) {
<<<<<<< HEAD
		String[] parts = line.split("|", 7); // Split into 7 parts
		if (parts.length == 7) {
			return new Property(parts[0].trim(),
					   PropertyType.valueOf(parts[1].trim()), // Parse to double
					   Double.parseDouble(parts[2].trim()), // Parse to double
					   Double.parseDouble(parts[3].trim()), // Assuming it's a String
					   Address.fromString(parts[4].trim()), // Assuming it's an enum
					   PropertyStat.valueOf(parts[5].trim()), // Assuming it's an enum
					   PropertyLegalStat.valueOf(parts[6].trim()) // Parse to boolean
               );
		}
		throw new IllegalArgumentException("Invalid property line: " + line);
	}
=======
        String[] parts = line.split("|", 7); // Split into 7 parts
        if (parts.length == 7) {
            return new Property(parts[0].trim(),
                    PropertyType.valueOf(parts[1].trim()), // Parse to double
                    Double.parseDouble(parts[2].trim()), // Parse to double
                    Double.parseDouble(parts[3].trim()), // Assuming it's a String
                    Address.fromString(parts[4].trim()), // Assuming it's an enum
                    PropertyStat.valueOf(parts[5].trim()), // Assuming it's an enum
                    PropertyLegalStat.valueOf(parts[6].trim()) // Parse to boolean
            );
        }
        throw new IllegalArgumentException("Invalid property line: " + line);
    }
>>>>>>> bbe91b0b3afe8051971871af7cac10193129bedc
}

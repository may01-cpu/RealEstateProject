package model;

public record Address(String country, String wilaya, String dayra, String street) {

    public Address( String country, String wilaya, String dayra,String street) {
        this.country = country;
        this.wilaya = wilaya;
        this.dayra = dayra;
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

    @Override
    public String toString() {
        return country + ',' + wilaya + ','+ dayra +',' + street;
    }
}
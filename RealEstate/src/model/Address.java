package model;

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
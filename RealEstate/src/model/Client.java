package model;

public class Client extends User{
    private ClientType type;


    public Client(String id, String firstName, String lastName, String email, String phoneNumber, String password, UserType userType, ClientType clientType) {
        super( id, firstName, lastName, email, phoneNumber, password);  // Call User constructor
        this.type = clientType;  // Set the ClientType for this client
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }
}

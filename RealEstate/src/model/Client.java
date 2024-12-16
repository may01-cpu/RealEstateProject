package model;

public class Client extends User{
    private ClientType type;


    public Client(String firstName, String lastName, String psw, String phoneNum, String email, String ID, UserType userType, ClientType clientType) {
        super(firstName, lastName, psw, phoneNum, email, ID, userType);  // Call User constructor
        this.type = clientType;  // Set the ClientType for this client
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }
}

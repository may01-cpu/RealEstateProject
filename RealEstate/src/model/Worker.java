package model;

public class Worker extends User  {

    private String role;

    public Worker(String firstName, String lastName, String email, String phoneNum, String password, String id, String role ) {
        super(firstName, lastName, email, phoneNum, password, id ,UserType.WORKER);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void assignProperty(Client client, String property) {
        System.out.println("Property " + property + " assigned to client " + client.getFirstName() + " " + client.getLastName());
    }
}

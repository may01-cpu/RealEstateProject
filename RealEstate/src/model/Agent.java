package model;

<<<<<<< HEAD:RealEstate/src/model/Worker.java
public class Worker extends User  {

=======
public class Agent extends User {
>>>>>>> 5ce9af05c10cc3837bf716a1525f282bb208b644:RealEstate/src/model/Agent.java
    private String role;

    public Agent(String firstName, String lastName, String email, String phoneNum, String password, String id, String role) {
        super(firstName, lastName, email, phoneNum, password, id);
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

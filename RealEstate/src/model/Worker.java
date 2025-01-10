package model;

public class Worker extends User  {


    public Worker(String id, String lastName, String firstName, String email, String phoneNum, String password ) {
        super(id, firstName, lastName, email, phoneNum, password);
    }

//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public void assignProperty(Client client, String property) {
//        System.out.println("Property " + property + " assigned to client " + client.getFirstName() + " " + client.getLastName());
//    }
}

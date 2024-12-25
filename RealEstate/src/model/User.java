package model;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email; 
    private String phoneNumber;
    private String password;
    private UserType userType;
    

<<<<<<< HEAD
    public User(String id, String firstName, String lastName, String email, String phoneNumber, String password) {

=======
    public User(String id, String firstName, String lastName, String email, String phoneNumber, String password, UserType userType) {
        this.id = id;
>>>>>>> 383f3d1f4ae8b643ca0c148cf3042e351728c5a7
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
<<<<<<< HEAD
        this.id = id;
    
=======
        this.userType = userType;
>>>>>>> 383f3d1f4ae8b643ca0c148cf3042e351728c5a7
    }

  
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getId() {
        return id;
    }

   
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName; 
    }

    public void setEmail(String email) {
        this.email = email; 
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
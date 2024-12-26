package model;

import java.time.LocalDateTime;
import utils.IDGenerator;

public class Appointment {

    private String idAppointment;   //id client?/
    private LocalDateTime dateTime; 
    private AppointmentState state; 
    private LocalDateTime createdAt; // When the appointment was created
    private Worker createdBy; // Worker who created the appointment 
    
    // Constructeur principal
    public Appointment( LocalDateTime dateTime, AppointmentState state, Worker createdBy) {
                        if (dateTime == null) throw new IllegalArgumentException("DateTime cannot be null.");
                        if (dateTime.isBefore(LocalDateTime.now())) {
                            throw new IllegalArgumentException("Appointment dateTime cannot be in the past.");
                        }
                        if (state == null) throw new IllegalArgumentException("State cannot be null.");
                        if (createdBy == null) throw new IllegalArgumentException("CreatedBy (Worker) cannot be null.");

        this.idAppointment = IDGenerator.generateAppointmentID();
        this.dateTime = dateTime;
        this.state = state;
        this.createdAt = LocalDateTime.now();
        this.createdBy = createdBy;
    }

    //get & set

    public String getIdAppointment() {
        return idAppointment;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        if (dateTime == null) throw new IllegalArgumentException("DateTime cannot be null.");
        if (dateTime.isBefore(LocalDateTime.now())) throw new IllegalArgumentException("Appointment dateTime cannot be in the past.");
        this.dateTime = dateTime;
    }

    public AppointmentState getState() {
        return state;
    }

    public void setState(AppointmentState state) {
        if (state == null) throw new IllegalArgumentException("State cannot be null.");
        this.state = state;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }  //il n'y a pas de setter pour createdAt 

    public Worker getCreatedBy() {
        return createdBy;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "idAppointment='" + idAppointment + '\'' +
                ", dateTime=" + dateTime +
                ", state=" + state +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy.getFirstName() +" " +createdBy.getLastName() +
                '}';
    }



}

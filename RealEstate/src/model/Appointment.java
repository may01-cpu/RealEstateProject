package model;

import java.time.LocalDateTime;

public class Appointment {

    private String idAppointment; 
    private LocalDateTime dateTime; 
    private AppointmentState state; 
    private LocalDateTime createdAt; // When the appointment was created
    private Agent createdBy; // Worker who created the appointment
    
    public Appointment(Client client, Property property, LocalDateTime dateTime, 
                       AppointmentState state, Agent createdBy) {
        if (client == null || property == null || dateTime == null || state == null || createdBy == null) {
            throw new IllegalArgumentException("All parameters must be non-null.");
        }

        this.idAppointment = client.getId() + "_" + property.getIdProperty(idAppointment) + "_" + dateTime.toString();
        this.dateTime = dateTime;
        this.state = state;
        this.createdAt = LocalDateTime.now();
        this.createdBy = createdBy;
    }

    public String getIdAppointment() {
        return idAppointment;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        if (dateTime == null) throw new IllegalArgumentException("DateTime cannot be null.");
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
    }

    public Agent getCreatedBy() {
        return createdBy;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "idAppointment='" + idAppointment + '\'' +
                ", dateTime=" + dateTime +
                ", state=" + state +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy.getFirstName() +
                '}';
    }

}

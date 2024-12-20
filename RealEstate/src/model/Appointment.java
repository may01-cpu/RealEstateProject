package model;
import java.time.LocalDateTime;

public class Appointment {
	private   String         idAppointment;  /*IDClient,IDProperty;  we don't have to use it (in database id_ap =idClient*+idProperty* and date to be unique to */
    private   LocalDateTime  dateTime ;
    private   String         state ;
    private   LocalDateTime  createdAt;
    private   Worker         createdW;
	
    
    public Appointment(String idAppointment, LocalDateTime dateTime, String state, LocalDateTime createdAt,
	p		model.Worker createdW ,Client client , Property Property) {
    	
		super();
		this.idAppointment = client.getId() + "_" + property.getidProperty()+ "_" + dateTime.toString();
		this.dateTime = dateTime;
		this.state = state;
		this.createdAt = createdAt;
		this.createdW = createdW;
	}


	public String getIdAppointment() {
		return idAppointment;
	}


	public void setIdAppointment(String idAppointment) {
		this.idAppointment = idAppointment;
	}


	public LocalDateTime getDateTime() {
		return dateTime;
	}


	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public Worker getCreatedW() {
		return createdW;
	}


	public void setCreatedW(Worker createdW) {
		this.createdW = createdW;
	}


	@Override
	public String toString() {

		 return "Appointment{" +
               "idAppointment='" + idAppointment + '\'' +
               ", dateTime=" + dateTime +
               ", state='" + state + '\'' +
               ", createdAt=" + createdAt +
               ", createdBy=" + createdW.getName() +
               '}';
	}



}




import java.time.LocalDateTime;
import model.AppointmentState;
import model.Worker;
import service.AppointmentService;


public class Main {

    public static void main(String[] args) {
        
        // Creating a Worker instance who will create the appointments
        Worker worker = new Worker("Alice", "Smith", "alice.smith@email.com", "123456789", "password", "W1", "Agent");

        // Create an instance of AppointmentService
        AppointmentService appointmentService = new AppointmentService();

        // Creating some Appointment objects
        LocalDateTime dateTime1 = LocalDateTime.now().plusDays(1); // Appointment for tomorrow
        LocalDateTime dateTime2 = LocalDateTime.now().plusDays(2); // Appointment for the day after tomorrow

        // Appointment states
        AppointmentState state1 = AppointmentState.SCHEDULED; // Scheduled state
        AppointmentState state2 = AppointmentState.COMPLETED;

        // Create the appointments
        appointmentService.createAppointment(dateTime1, state1, worker);  // Appointment 1
        appointmentService.createAppointment(dateTime2, state2, worker);  // Appointment 2

        // Listing all appointments
        System.out.println("List of Appointments:");
        appointmentService.listAppointments();



    }
}

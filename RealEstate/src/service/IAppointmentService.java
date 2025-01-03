package service;

import java.util.Scanner;
import model.Worker;

public interface IAppointmentService {

    void createAppointment(Scanner scanner, Worker createdBy);
    void listAppointments();
    void saveAppointmentsToFile();
    void loadAppointmentsFromFile();
    void removeAppointment(Scanner scanner);
    void updateAppointmentState(Scanner scanner);
    void viewAppointmentsForClient(String clientId);
}

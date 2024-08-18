/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import model.Appointment;
import java.sql.SQLException;
import java.util.List;

/**
 * The AppointmentDaoInterface defines the operations that can be performed
 * on Appointment objects in the database. It provides methods for retrieving,
 * updating, and managing appointments.
 */
public interface AppointmentDaoInterface {

    // Retrieves a list of upcoming appointments for a specific doctor.
    List<Appointment> getUpcomingAppointmentsByDoctorID(int doctorID) throws SQLException;

    // Retrieves a list of past appointments for a specific doctor.
    List<Appointment> getPastAppointmentsByDoctorID(int doctorID) throws SQLException;

    // Adds a note to a specific appointment.
    void addNoteToAppointment(int appointmentID, String note) throws SQLException;

    // Retrieves a list of upcoming appointments for a specific patient.
    List<Appointment> getUpcomingAppointmentsByPatientID(int patientID) throws SQLException;

    // Retrieves a list of past appointments for a specific patient.
    List<Appointment> getPastAppointmentsByPatientID(int patientID) throws SQLException;

    // Retrieves a specific appointment by its ID.
    Appointment getAppointmentByID(int appointmentID) throws SQLException;

    // Updates the details of a specific appointment.
    void updateAppointment(Appointment appointment) throws SQLException;

    // Books a new appointment.
    void bookAppointment(Appointment appointment) throws SQLException;

    // Deletes a specific appointment by its ID.
    void deleteAppointment(int appointmentID) throws SQLException;
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import model.Appointment;
import java.sql.SQLException;
import java.util.List;

public interface AppointmentDaoInterface {

    List<Appointment> getUpcomingAppointmentsByDoctorID(int doctorID) throws SQLException;

    List<Appointment> getPastAppointmentsByDoctorID(int doctorID) throws SQLException;

    void addNoteToAppointment(int appointmentID, String note) throws SQLException;

    List<Appointment> getUpcomingAppointmentsByPatientID(int patientID) throws SQLException;

    List<Appointment> getPastAppointmentsByPatientID(int patientID) throws SQLException;

    Appointment getAppointmentByID(int appointmentID) throws SQLException;

    void updateAppointment(Appointment appointment) throws SQLException;

    void bookAppointment(Appointment appointment) throws SQLException;

    void deleteAppointment(int appointmentID) throws SQLException;
}

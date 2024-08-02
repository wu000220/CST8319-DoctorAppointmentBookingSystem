/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import model.Appointment;
import dataaccesslayer.AppointmentDao;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;

public class AppointmentBusinessLogic {

    private AppointmentDao appointmentDao = new AppointmentDao();

    // Method to validate note input
    public boolean validateNoteInput(String note) {
        // Basic validation: ensure note is not null or empty
        return note != null && !note.trim().isEmpty();
    }

    // Method to add note to appointment
    public void addNoteToAppointment(int appointmentID, String note) throws SQLException {
        if (validateNoteInput(note)) {
            appointmentDao.addNoteToAppointment(appointmentID, note);
        } else {
            throw new IllegalArgumentException("Invalid note input");
        }
    }
    // Method to validate appointment inputs
    public boolean validateAppointmentInputs(int doctorID, int patientID, Date appointmentDate, Time appointmentTime, String reason) {
        // Basic validation: ensure inputs are not null and date/time is in the future
        if (doctorID <= 0 || patientID <= 0 || appointmentDate == null || appointmentTime == null || reason == null || reason.trim().isEmpty()) {
            return false;
        }

        // Check if appointment date and time are in the future
        Date currentDate = new Date(System.currentTimeMillis());
        Time currentTime = new Time(System.currentTimeMillis());

        if (appointmentDate.before(currentDate) || (appointmentDate.equals(currentDate) && appointmentTime.before(currentTime))) {
            return false;
        }

        return true;
    }

    // Method to book appointment
    public void bookAppointment(int doctorID, int patientID, Date appointmentDate, Time appointmentTime, String reason) throws SQLException {
        if (validateAppointmentInputs(doctorID, patientID, appointmentDate, appointmentTime, reason)) {
            Appointment appointment = new Appointment();
            appointment.setDoctorID(doctorID);
            appointment.setPatientID(patientID);
            appointment.setAppointmentDate(appointmentDate);
            appointment.setAppointmentTime(appointmentTime);
            appointment.setReason(reason);

            appointmentDao.bookAppointment(appointment);
        } else {
            throw new IllegalArgumentException("Invalid appointment details");
        }
    }
    // Method to cancel an appointment
    public void cancelAppointment(int appointmentID) throws SQLException {
        if (appointmentID <= 0) {
            throw new IllegalArgumentException("Invalid appointment ID");
        }
        
        appointmentDao.deleteAppointment(appointmentID);
    }
    public void updateAppointment(int appointmentID, Date appointmentDate, Time appointmentTime, String reason) throws SQLException {
        // Fetch the existing appointment
        Appointment appointment = appointmentDao.getAppointmentByID(appointmentID);

        if (appointment == null) {
            throw new IllegalArgumentException("Appointment not found");
        }

        // Validate the new date and time
        validateAppointmentDateTime(appointmentDate, appointmentTime);

        // Validate reason (e.g., check for empty or null reason)
        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException("Reason cannot be empty");
        }

        // Update the appointment details
        appointment.setAppointmentDate(appointmentDate);
        appointment.setAppointmentTime(appointmentTime);
        appointment.setReason(reason);

        // Save the updated appointment
        appointmentDao.updateAppointment(appointment);
    }

   
    private void validateAppointmentDateTime(Date appointmentDate, Time appointmentTime) {
        // Example validation: Check if the date and time are in the future
        if (appointmentDate.before(new Date(System.currentTimeMillis())) ||
            (appointmentDate.equals(new Date(System.currentTimeMillis())) && appointmentTime.before(new Time(System.currentTimeMillis())))) {
            throw new IllegalArgumentException("Appointment date and time must be in the future");
        }
    }
}

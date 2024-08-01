/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dataaccesslayer;

import model.Appointment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao {
    private Connection getConnection() throws SQLException {
        return DataSource.getConnection();
    }

    public List<Appointment> getUpcomingAppointmentsByDoctorID(int doctorID) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT a.appointmentID, a.appointmentDate, a.appointmentTime, a.reason, a.note, p.patientName " +
                     "FROM Appointment a " +
                     "JOIN Patient p ON a.patientID = p.patientID " +
                     "WHERE a.doctorID = ? AND a.appointmentDate >= CURDATE() " +
                     "ORDER BY a.appointmentDate, a.appointmentTime";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, doctorID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentID(rs.getInt("appointmentID"));
                appointment.setAppointmentDate(rs.getDate("appointmentDate"));
                appointment.setAppointmentTime(rs.getTime("appointmentTime"));
                appointment.setReason(rs.getString("reason"));
                appointment.setNote(rs.getString("note"));
                appointment.setPatientName(rs.getString("patientName")); // Set patient name
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public List<Appointment> getPastAppointmentsByDoctorID(int doctorID) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT a.appointmentID, a.appointmentDate, a.appointmentTime, a.reason, a.note, p.patientName " +
                     "FROM Appointment a " +
                     "JOIN Patient p ON a.patientID = p.patientID " +
                     "WHERE a.doctorID = ? AND a.appointmentDate < CURDATE() " +
                     "ORDER BY a.appointmentDate DESC, a.appointmentTime DESC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, doctorID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentID(rs.getInt("appointmentID"));
                appointment.setAppointmentDate(rs.getDate("appointmentDate"));
                appointment.setAppointmentTime(rs.getTime("appointmentTime"));
                appointment.setReason(rs.getString("reason"));
                appointment.setNote(rs.getString("note"));
                appointment.setPatientName(rs.getString("patientName"));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public void addNoteToAppointment(int appointmentID, String note) throws SQLException {
        String sql = "UPDATE Appointment SET note = ? WHERE appointmentID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, note);
            stmt.setInt(2, appointmentID);
            stmt.executeUpdate();
        }
    }

    public List<Appointment> getUpcomingAppointmentsByPatientID(int patientID) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT a.appointmentID, a.appointmentDate, a.appointmentTime, a.reason, a.note, d.doctorName " +
                     "FROM Appointment a " +
                     "JOIN Doctor d ON a.doctorID = d.doctorID " +
                     "WHERE a.patientID = ? AND a.appointmentDate >= CURDATE() " +
                     "ORDER BY a.appointmentDate, a.appointmentTime";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentID(rs.getInt("appointmentID"));
                appointment.setAppointmentDate(rs.getDate("appointmentDate"));
                appointment.setAppointmentTime(rs.getTime("appointmentTime"));
                appointment.setReason(rs.getString("reason"));
                appointment.setNote(rs.getString("note"));
                appointment.setDoctorName(rs.getString("doctorName"));
                appointments.add(appointment);
            }
        }
        return appointments;
    }

    public List<Appointment> getPastAppointmentsByPatientID(int patientID) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT a.appointmentID, a.appointmentDate, a.appointmentTime, a.reason, a.note, d.doctorName " +
                     "FROM Appointment a " +
                     "JOIN Doctor d ON a.doctorID = d.doctorID " +
                     "WHERE a.patientID = ? AND a.appointmentDate < CURDATE() " +
                     "ORDER BY a.appointmentDate DESC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentID(rs.getInt("appointmentID"));
                appointment.setAppointmentDate(rs.getDate("appointmentDate"));
                appointment.setAppointmentTime(rs.getTime("appointmentTime"));
                appointment.setReason(rs.getString("reason"));
                appointment.setNote(rs.getString("note"));
                appointment.setDoctorName(rs.getString("doctorName"));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public Appointment getAppointmentByID(int appointmentID) throws SQLException {
        Appointment appointment = null;
        String sql = "SELECT a.appointmentID, a.appointmentDate, a.appointmentTime, a.reason, a.note, d.doctorName, p.patientName " +
                     "FROM Appointment a " +
                     "JOIN Doctor d ON a.doctorID = d.doctorID " +
                     "JOIN Patient p ON a.patientID = p.patientID " +
                     "WHERE a.appointmentID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointmentID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                appointment = new Appointment();
                appointment.setAppointmentID(rs.getInt("appointmentID"));
                appointment.setAppointmentDate(rs.getDate("appointmentDate"));
                appointment.setAppointmentTime(rs.getTime("appointmentTime"));
                appointment.setReason(rs.getString("reason"));
                appointment.setNote(rs.getString("note"));
                appointment.setDoctorName(rs.getString("doctorName"));
                appointment.setPatientName(rs.getString("patientName"));
            }
        }
        return appointment;
    }

    public void updateAppointment(Appointment appointment) throws SQLException {
        String sql = "UPDATE Appointment SET appointmentDate = ?, appointmentTime = ?, reason = ? WHERE appointmentID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(appointment.getAppointmentDate().getTime()));
            stmt.setTime(2, new java.sql.Time(appointment.getAppointmentTime().getTime()));
            stmt.setString(3, appointment.getReason());
            stmt.setInt(4, appointment.getAppointmentID());

            stmt.executeUpdate();
        }
    }
    
    public void bookAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO Appointment (doctorID, patientID, appointmentDate, appointmentTime, reason) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointment.getDoctorID());
            stmt.setInt(2, appointment.getPatientID());
            stmt.setDate(3, appointment.getAppointmentDate());
            stmt.setTime(4, appointment.getAppointmentTime());
            stmt.setString(5, appointment.getReason());
            stmt.executeUpdate();
        }
    }
    public void deleteAppointment(int appointmentID) throws SQLException {
        String sql = "DELETE FROM Appointment WHERE appointmentID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointmentID);
            stmt.executeUpdate();
        }
}

}

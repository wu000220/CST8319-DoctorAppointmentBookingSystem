/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Doctor;
import model.Patient;

public class LoginDAO {

    // Authenticate Doctor
    public Doctor authenticateDoctor(String email, String password) throws SQLException {
        Connection connection = DataSource.getConnection();
        String query = "SELECT * FROM Doctor WHERE doctorEmail = ? AND doctorPwd = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorID(rs.getInt("doctorID"));
                doctor.setDoctorName(rs.getString("doctorName"));
                doctor.setDoctorAddress(rs.getString("doctorAddress"));
                doctor.setDoctorMobile(rs.getString("doctorMobile"));
                doctor.setDoctorEmail(rs.getString("doctorEmail"));
                doctor.setDoctorPwd(rs.getString("doctorPwd"));
                return doctor;
            }
        }
        return null;
    }

    // Authenticate Patient
    public Patient authenticatePatient(String email, String password) throws SQLException {
        Connection connection = DataSource.getConnection();
        String query = "SELECT * FROM Patient WHERE patientEmail = ? AND patientPwd = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientID(rs.getInt("patientID"));
                patient.setPatientName(rs.getString("patientName"));
                patient.setPatientAddress(rs.getString("patientAddress"));
                patient.setPatientMobile(rs.getString("patientMobile"));
                patient.setPatientEmail(rs.getString("patientEmail"));
                patient.setPatientPwd(rs.getString("patientPwd"));
                return patient;
            }
        }
        return null;
    }
}

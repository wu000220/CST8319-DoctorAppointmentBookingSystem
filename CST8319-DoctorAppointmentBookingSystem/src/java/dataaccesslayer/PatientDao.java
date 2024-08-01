/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dataaccesslayer;

import model.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDao {
    private Connection getConnection() throws SQLException {
        return DataSource.getConnection();
    }

    public void updatePatient(Patient patient) throws SQLException {
        String sql = "UPDATE Patient SET patientName = ?, patientAddress = ?, patientMobile = ?, patientEmail = ?, patientPwd = ? WHERE patientID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getPatientName());
            stmt.setString(2, patient.getPatientAddress());
            stmt.setString(3, patient.getPatientMobile());
            stmt.setString(4, patient.getPatientEmail());
            stmt.setString(5, patient.getPatientPwd());
            stmt.setInt(6, patient.getPatientID());

            stmt.executeUpdate();
        }
    }

    public Patient getPatientByID(int patientID) throws SQLException {
        String sql = "SELECT * FROM Patient WHERE patientID = ?";
        Patient patient = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                patient = new Patient();
                patient.setPatientID(rs.getInt("patientID"));
                patient.setPatientName(rs.getString("patientName"));
                patient.setPatientAddress(rs.getString("patientAddress"));
                patient.setPatientMobile(rs.getString("patientMobile"));
                patient.setPatientEmail(rs.getString("patientEmail"));
                patient.setPatientPwd(rs.getString("patientPwd"));
            }
        }

        return patient;
    }

    public void registerPatient(Patient patient) throws SQLException {
        String query = "INSERT INTO Patient (patientName, patientAddress, patientMobile, patientEmail, patientPwd) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, patient.getPatientName());
            statement.setString(2, patient.getPatientAddress());
            statement.setString(3, patient.getPatientMobile());
            statement.setString(4, patient.getPatientEmail());
            statement.setString(5, patient.getPatientPwd());
            statement.executeUpdate();
        }
    }
}

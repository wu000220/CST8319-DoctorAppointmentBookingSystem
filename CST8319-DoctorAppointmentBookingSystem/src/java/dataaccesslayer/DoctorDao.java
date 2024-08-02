/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dataaccesslayer;

import model.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao implements DoctorDaoInterface{

    private Connection getConnection() throws SQLException {
        return DataSource.getConnection(); // Ensure this method is correctly implemented
    }

    @Override
    public Doctor getDoctorByID(int doctorID) {
        Doctor doctor = null;
        String sql = "SELECT * FROM Doctor WHERE doctorID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, doctorID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                doctor = new Doctor();
                doctor.setDoctorID(rs.getInt("doctorID"));
                doctor.setDoctorName(rs.getString("doctorName"));
                doctor.setDoctorAddress(rs.getString("doctorAddress"));
                doctor.setDoctorMobile(rs.getString("doctorMobile"));
                doctor.setDoctorEmail(rs.getString("doctorEmail"));
                doctor.setDoctorPwd(rs.getString("doctorPwd"));
                doctor.setSpecialization(rs.getString("specialization"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctor;
    }

    @Override
    public void updateDoctor(Doctor doctor) throws SQLException {
        String sql = "UPDATE Doctor SET doctorName = ?, doctorAddress = ?, doctorMobile = ?, doctorEmail = ?, doctorPwd = ?, specialization = ? WHERE doctorID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, doctor.getDoctorName());
            stmt.setString(2, doctor.getDoctorAddress());
            stmt.setString(3, doctor.getDoctorMobile());
            stmt.setString(4, doctor.getDoctorEmail());
            stmt.setString(5, doctor.getDoctorPwd());
            stmt.setString(6, doctor.getSpecialization());
            stmt.setInt(7, doctor.getDoctorID());

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT doctorID, doctorName, specialization FROM Doctor";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorID(rs.getInt("doctorID"));
                doctor.setDoctorName(rs.getString("doctorName"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    @Override
    public void registerDoctor(Doctor doctor) throws SQLException {
        String query = "INSERT INTO Doctor (doctorName, doctorAddress, doctorMobile, doctorEmail, doctorPwd, specialization) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctor.getDoctorName());
            statement.setString(2, doctor.getDoctorAddress());
            statement.setString(3, doctor.getDoctorMobile());
            statement.setString(4, doctor.getDoctorEmail());
            statement.setString(5, doctor.getDoctorPwd());
            statement.setString(6, doctor.getSpecialization());
            statement.executeUpdate();
        }
    }
}

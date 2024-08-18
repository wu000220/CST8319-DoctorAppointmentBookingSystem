/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import model.Doctor;
import java.sql.SQLException;
import java.util.List;

/**
 * This interface defines the operations for interacting with Doctor data in the database.
 */
public interface DoctorDaoInterface {

    // Retrieves a doctor by their ID.
    Doctor getDoctorByID(int doctorID) throws SQLException;

    // Updates the details of an existing doctor.
    void updateDoctor(Doctor doctor) throws SQLException;

    // Retrieves a list of all doctors.
    List<Doctor> getAllDoctors() throws SQLException;

    // Registers a new doctor in the database.
    void registerDoctor(Doctor doctor) throws SQLException;
}

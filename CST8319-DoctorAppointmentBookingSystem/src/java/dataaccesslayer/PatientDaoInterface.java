/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import model.Patient;
import java.sql.SQLException;

/**
 * Defines the operations for managing patient data in the database.
 */
public interface PatientDaoInterface {

    // Updates the details of an existing patient.
    void updatePatient(Patient patient) throws SQLException;

    // Retrieves a patient by their ID.
    Patient getPatientByID(int patientID) throws SQLException;

    // Registers a new patient in the database.
    void registerPatient(Patient patient) throws SQLException;
}

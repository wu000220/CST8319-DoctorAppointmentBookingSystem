/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;


import dataaccesslayer.PatientDao;
import model.Patient;
import java.sql.SQLException;

/**
 * Handles business logic related to patient operations.
 */
public class PatientBusinessLogic {

    private PatientDao patientDao = new PatientDao();

    // Validates the details of a patient.
    public boolean validatePatient(Patient patient) {

        if (patient.getPatientName() == null || patient.getPatientName().isEmpty()) {
            return false; // Name is required
        }
        if (patient.getPatientEmail() == null || !patient.getPatientEmail().contains("@")) {
            return false; // Email must be valid
        }
        return true;
    }

    // Registers a new patient if the patient details are valid.
    public void registerPatient(Patient patient) throws SQLException {
        if (validatePatient(patient)) {
            patientDao.registerPatient(patient);
        } else {
            throw new IllegalArgumentException("Invalid patient details");
        }
    }

    // Updates an existing patient's information if the details are valid.
    public void updatePatient(Patient patient) throws SQLException {
        if (validatePatient(patient)) {
            patientDao.updatePatient(patient);
        } else {
            throw new IllegalArgumentException("Invalid patient details");
        }
    }

    // Retrieves a patient's information by their ID.
    public Patient getPatientByID(int patientID) throws SQLException {
        return patientDao.getPatientByID(patientID);
    }
    
    // Updates a patient's profile using individual fields.
    public void updatePatient(int patientID, String name, String email, String phoneNumber, String address, String password) throws SQLException {
        // Validate input fields
        validatePatientDetails(name, email, phoneNumber, address, password);

        // Create a Patient object with the new details
        Patient patient = new Patient();
        patient.setPatientID(patientID);
        patient.setPatientName(name);
        patient.setPatientEmail(email);
        patient.setPatientMobile(phoneNumber);
        patient.setPatientAddress(address);
        patient.setPatientPwd(password);

        // Update the patient's profile in the database
        patientDao.updatePatient(patient);
    }

    // Validates individual patient details.
    private void validatePatientDetails(String name, String email, String phoneNumber, String address, String password) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
    }
}

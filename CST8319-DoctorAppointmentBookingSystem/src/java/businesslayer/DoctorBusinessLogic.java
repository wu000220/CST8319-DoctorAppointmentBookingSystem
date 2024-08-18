/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;



import dataaccesslayer.DoctorDao;
import model.Doctor;
import java.sql.SQLException;

/**
 * Handles business logic related to doctors.
 */
public class DoctorBusinessLogic {

    private DoctorDao doctorDao = new DoctorDao();

    // Validates a doctor's details to ensure they are correct.
    public boolean validateDoctor(Doctor doctor) {
        if (doctor.getDoctorName() == null || doctor.getDoctorName().isEmpty()) {
            return false; // Name is required
        }
        if (doctor.getDoctorEmail() == null || !doctor.getDoctorEmail().contains("@")) {
            return false; // Email must be valid
        }
        return true;
    }

    // Registers a new doctor if the doctor's details are valid.
    public void registerDoctor(Doctor doctor) throws SQLException {
        if (validateDoctor(doctor)) {
            doctorDao.registerDoctor(doctor);
        } else {
            throw new IllegalArgumentException("Invalid doctor details");
        }
    }

    // Updates an existing doctor's details if the details are valid.
    public void updateDoctor(Doctor doctor) throws SQLException {
        if (validateDoctor(doctor)) {
            doctorDao.updateDoctor(doctor);
        } else {
            throw new IllegalArgumentException("Invalid doctor details");
        }
    }

    // Retrieves a doctor by their ID.
    public Doctor getDoctorByID(int doctorID) throws SQLException {
        return doctorDao.getDoctorByID(doctorID);
    }
    
    // Updates a doctor's profile with new details.
    public void updateDoctor(int doctorID, String name, String address, String mobile, String email, String password, String specialization) throws SQLException {
        // Validate input fields
        validateDoctorDetails(name, address, mobile, email, password);

        // Create a Doctor object with the new details
        Doctor doctor = new Doctor();
        doctor.setDoctorID(doctorID);
        doctor.setDoctorName(name);
        doctor.setDoctorAddress(address);
        doctor.setDoctorMobile(mobile);
        doctor.setDoctorEmail(email);
        doctor.setDoctorPwd(password);
        doctor.setSpecialization(specialization);

        // Update the doctor's profile in the database
        doctorDao.updateDoctor(doctor);
    }

    // Validates individual doctor details for correctness.
    private void validateDoctorDetails(String name, String address, String mobile, String email, String password) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
        if (mobile == null || mobile.trim().isEmpty()) {
            throw new IllegalArgumentException("Mobile number cannot be empty");
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
    }
}

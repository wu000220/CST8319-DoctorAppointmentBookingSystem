/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;



import dataaccesslayer.DoctorDao;
import model.Doctor;
import java.sql.SQLException;

public class DoctorBusinessLogic {

    private DoctorDao doctorDao = new DoctorDao();

    public boolean validateDoctor(Doctor doctor) {
        // Example validation logic
        if (doctor.getDoctorName() == null || doctor.getDoctorName().isEmpty()) {
            return false; // Name is required
        }
        if (doctor.getDoctorEmail() == null || !doctor.getDoctorEmail().contains("@")) {
            return false; // Email must be valid
        }
        // Add more validations as needed
        return true;
    }

    public void registerDoctor(Doctor doctor) throws SQLException {
        if (validateDoctor(doctor)) {
            doctorDao.registerDoctor(doctor);
        } else {
            throw new IllegalArgumentException("Invalid doctor details");
        }
    }

    public void updateDoctor(Doctor doctor) throws SQLException {
        if (validateDoctor(doctor)) {
            doctorDao.updateDoctor(doctor);
        } else {
            throw new IllegalArgumentException("Invalid doctor details");
        }
    }

    public Doctor getDoctorByID(int doctorID) throws SQLException {
        return doctorDao.getDoctorByID(doctorID);
    }
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

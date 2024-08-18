/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import dataaccesslayer.LoginDAO;
import model.Doctor;
import model.Patient;

/**
 * Handles business logic related to user authentication.
 */
public class LoginBusinessLogic {

    private LoginDAO loginDAO = new LoginDAO();

    // Authenticates a doctor with the provided email and password.
    public Doctor authenticateDoctor(String email, String password) throws Exception {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Email and password must not be empty");
        }
        
        return loginDAO.authenticateDoctor(email, password);
    }

    // Authenticates a patient with the provided email and password.
    public Patient authenticatePatient(String email, String password) throws Exception {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Email and password must not be empty");
        }
        
        return loginDAO.authenticatePatient(email, password);
    }
}

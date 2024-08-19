/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
/*
 * Servlet implementation class RegisterServlet
 * This servlet handles user registration for both doctors and patients.
 * It validates the registration details, creates user objects, and invokes 
 * the appropriate business logic to register the user in the system.
 */

package controller;

import businesslayer.DoctorBusinessLogic;
import businesslayer.PatientBusinessLogic;
import businesslayer.Validation;
import businesslayer.ValidationException;
import model.Doctor;
import model.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Instances of business logic classes for handling doctor and patient registration
    private DoctorBusinessLogic doctorBusinessLogic = new DoctorBusinessLogic();
    private PatientBusinessLogic patientBusinessLogic = new PatientBusinessLogic();
    private Validation validation = new Validation();

    // Handles POST requests to process user registration
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve registration details from request parameters
        String role = request.getParameter("role");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String mobile = request.getParameter("mobile");

        try {
            // Validate password according to business rules
            validation.validatePasswordImpl(password);
            
            // Register user based on the role specified
            if (role.equals("doctor")) {
                // Create and populate Doctor object
                Doctor doctor = new Doctor();
                doctor.setDoctorName(name);
                doctor.setDoctorEmail(email);
                doctor.setDoctorPwd(password);
                doctor.setDoctorAddress(address);
                doctor.setDoctorMobile(mobile);
                doctor.setSpecialization(""); // Default specialization

                // Register doctor using business logic
                doctorBusinessLogic.registerDoctor(doctor);
            } else if (role.equals("patient")) {
                // Create and populate Patient object
                Patient patient = new Patient();
                patient.setPatientName(name);
                patient.setPatientEmail(email);
                patient.setPatientPwd(password);
                patient.setPatientAddress(address);
                patient.setPatientMobile(mobile);

                // Register patient using business logic
                patientBusinessLogic.registerPatient(patient);
            } else {
                // Handle case where an invalid role is provided
                throw new IllegalArgumentException("Invalid role specified.");
            }

            // Redirect to the login page after successful registration
            response.sendRedirect("index.jsp");
        } catch (ValidationException e) {
            // Handle errors related to password validation
            request.setAttribute("registerError", "Password validation failed: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            // Handle errors related to invalid role or other arguments
            request.setAttribute("registerError", e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle any other general errors that may occur
            request.setAttribute("registerError", "An error occurred while registering the profile. " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}

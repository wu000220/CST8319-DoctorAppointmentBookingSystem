/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
/*
 * Servlet implementation class UpdatePatientServlet
 * This servlet handles updating a patient's profile. It processes form submissions 
 * for updating patient details and updates the session with the new information.
 */

package controller;

import businesslayer.PatientBusinessLogic;
import model.Patient;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UpdateProfilePatientServlet")
public class UpdatePatientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Instance of the business logic class for patient management
    private PatientBusinessLogic patientBusinessLogic = new PatientBusinessLogic();

    // Handles POST requests for updating a patient's profile
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the current session and check if the patient is logged in
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute("patient");

        // Redirect to login page if no patient is found in the session
        if (patient == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Retrieve updated patient details from request parameters
        String patientName = request.getParameter("patientName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String password = request.getParameter("password");

        try {
            // Update the patient's profile using the business logic class
            patientBusinessLogic.updatePatient(
                patient.getPatientID(), 
                patientName, 
                email, 
                phoneNumber, 
                address, 
                password
            );

            // Update the session with the new patient details
            patient.setPatientName(patientName);
            patient.setPatientEmail(email);
            patient.setPatientMobile(phoneNumber);
            patient.setPatientAddress(address);
            patient.setPatientPwd(password);
            session.setAttribute("patient", patient);

            // Redirect to patient dashboard after successful update
            response.sendRedirect("patient.jsp");
        } catch (IllegalArgumentException e) {
            // Handle validation errors and display appropriate message
            e.printStackTrace();
            request.setAttribute("updateError", "Error updating profile: " + e.getMessage());
            request.getRequestDispatcher("viewProfilePatient.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle general exceptions and display appropriate message
            e.printStackTrace();
            request.setAttribute("updateError", "An error occurred while updating the profile.");
            request.getRequestDispatcher("viewProfilePatient.jsp").forward(request, response);
        }
    }
}

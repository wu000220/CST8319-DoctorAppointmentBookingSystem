/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
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

    private PatientBusinessLogic patientBusinessLogic = new PatientBusinessLogic();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute("patient");

        if (patient == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String patientName = request.getParameter("patientName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String password = request.getParameter("password");

        try {
            // Use the business logic class to update the patient's profile
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

            response.sendRedirect("patient.jsp");
        } catch (IllegalArgumentException e) {
            // Handle validation errors
            e.printStackTrace();
            request.setAttribute("updateError", "Error updating profile: " + e.getMessage());
            request.getRequestDispatcher("viewProfilePatient.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            request.setAttribute("updateError", "An error occurred while updating the profile.");
            request.getRequestDispatcher("viewProfilePatient.jsp").forward(request, response);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
/*
 * Servlet implementation class LoginServlet
 * This servlet handles the login process for both doctors and patients.
 * It authenticates users based on email and password, manages sessions,
 * and handles errors during the authentication process.
 */

package controller;

import businesslayer.LoginBusinessLogic;
import model.Doctor;
import model.Patient;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Instance of LoginBusinessLogic to handle authentication logic
    private LoginBusinessLogic loginBusinessLogic = new LoginBusinessLogic();

    // Handles POST requests to process login attempts
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve email and password from the request parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Attempt to authenticate as a doctor
            Doctor doctor = loginBusinessLogic.authenticateDoctor(email, password);
            if (doctor != null) {
                // If authentication is successful, create a session and set doctor details
                HttpSession session = request.getSession();
                session.setAttribute("doctor", doctor);
                session.setAttribute("doctorName", doctor.getDoctorName());
                session.setAttribute("doctorID", doctor.getDoctorID());
                // Redirect to doctor dashboard
                response.sendRedirect("doctor.jsp");
                return;
            }

            // Attempt to authenticate as a patient
            Patient patient = loginBusinessLogic.authenticatePatient(email, password);
            if (patient != null) {
                // If authentication is successful, create a session and set patient details
                HttpSession session = request.getSession();
                session.setAttribute("patient", patient);
                session.setAttribute("patientName", patient.getPatientName());
                session.setAttribute("patientID", patient.getPatientID());
                // Redirect to patient dashboard
                response.sendRedirect("patient.jsp");
                return;
            }

            // If authentication fails for both doctor and patient
            // Set error message and forward to the login page
            request.setAttribute("loginError", "Invalid email or password.");
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (IllegalArgumentException e) {
            // Handle specific case where input parameters are invalid
            request.setAttribute("loginError", e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle unexpected errors
            request.setAttribute("loginError", "An error occurred while processing your request.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}

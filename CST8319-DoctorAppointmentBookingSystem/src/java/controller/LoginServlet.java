/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
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

    // Initialize LoginBusinessLogic for authentication
    private LoginBusinessLogic loginBusinessLogic = new LoginBusinessLogic();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Doctor doctor = loginBusinessLogic.authenticateDoctor(email, password);
            if (doctor != null) {
                HttpSession session = request.getSession();
                session.setAttribute("doctor", doctor);
                session.setAttribute("doctorName", doctor.getDoctorName());
                session.setAttribute("doctorID", doctor.getDoctorID());
                response.sendRedirect("doctor.jsp");
                return;
            }

            Patient patient = loginBusinessLogic.authenticatePatient(email, password);
            if (patient != null) {
                HttpSession session = request.getSession();
                session.setAttribute("patient", patient);
                session.setAttribute("patientName", patient.getPatientName());
                session.setAttribute("patientID", patient.getPatientID());
                response.sendRedirect("patient.jsp");
                return;
            }

            // Authentication failed
            request.setAttribute("loginError", "Invalid email or password.");
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            request.setAttribute("loginError", e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("loginError", "An error occurred while processing your request.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}

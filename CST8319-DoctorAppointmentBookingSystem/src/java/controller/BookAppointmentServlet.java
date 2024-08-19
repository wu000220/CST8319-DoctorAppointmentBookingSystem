/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
/*
 * Servlet implementation class BookAppointmentServlet
 * This servlet handles the process of booking an appointment.
 * It retrieves appointment details from the request, interacts with the business logic to book the appointment,
 * and handles any exceptions that may occur during the process.
 */
package controller;

import businesslayer.AppointmentBusinessLogic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.SQLException;

@WebServlet("/BookAppointmentServlet")
public class BookAppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private AppointmentBusinessLogic appointmentBusinessLogic = new AppointmentBusinessLogic();
    // Handles POST requests to book an appointment
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve doctor ID from the request parameters
            int doctorID = Integer.parseInt(request.getParameter("doctorID"));
            // Retrieve patient ID from the session
            int patientID = (Integer) request.getSession().getAttribute("patientID");
            // Retrieve appointment date and time from the request parameters
            Date appointmentDate = Date.valueOf(request.getParameter("appointmentDate"));
            Time appointmentTime = Time.valueOf(request.getParameter("appointmentTime") + ":00");
            // Retrieve reason for the appointment from the request parameters
            String reason = request.getParameter("reason");
            // Attempt to book the appointment using business logic
            appointmentBusinessLogic.bookAppointment(doctorID, patientID, appointmentDate, appointmentTime, reason);
            // Redirect to patient dashboard after successful booking
            response.sendRedirect("patient.jsp"); // Redirect to patient dashboard
        } catch (IllegalArgumentException e) {
            // Handle specific case where input parameters might be invalid
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("bookTheDoctor.jsp").forward(request, response);
        } catch (SQLException e) {
            // Handle SQL-related exceptions, indicating a database issue
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error while booking appointment");
            request.getRequestDispatcher("bookTheDoctor.jsp").forward(request, response);
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unexpected error occurred");
            request.getRequestDispatcher("bookTheDoctor.jsp").forward(request, response);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
/*
 * Servlet implementation class CancelAppointmentServlet
 * This servlet handles the cancellation of appointments.
 * It retrieves the appointment ID from the request, interacts with the business logic to cancel the appointment,
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
import java.sql.SQLException;

@WebServlet("/CancelAppointmentServlet")
public class CancelAppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Instance of AppointmentBusinessLogic to handle business logic related to appointments
    private AppointmentBusinessLogic appointmentBusinessLogic = new AppointmentBusinessLogic();

    // Handles GET requests to cancel an appointment
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve appointment ID from the request parameters
            int appointmentID = Integer.parseInt(request.getParameter("appointmentID"));

            // Attempt to cancel the appointment using business logic
            appointmentBusinessLogic.cancelAppointment(appointmentID);

            // Redirect to patient dashboard after successful cancellation
            response.sendRedirect("patient.jsp");
        } catch (IllegalArgumentException e) {
            // Handle specific case where input parameters might be invalid
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("patient.jsp").forward(request, response);
        } catch (SQLException e) {
            // Handle SQL-related exceptions, indicating a database issue
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error while cancelling appointment");
            request.getRequestDispatcher("patient.jsp").forward(request, response);
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unexpected error occurred");
            request.getRequestDispatcher("patient.jsp").forward(request, response);
        }
    }
}

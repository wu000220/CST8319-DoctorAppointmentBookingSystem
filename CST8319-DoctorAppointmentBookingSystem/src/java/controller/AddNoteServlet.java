/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

/*
 * Servlet implementation class AddNoteServlet
 * This servlet handles the addition of notes to an appointment.
 * It receives the appointment ID and the note from the request,
 * updates the appointment in the database, and handles any exceptions
 * that may occur during the process.
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

@WebServlet("/AddNoteServlet")
public class AddNoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private AppointmentBusinessLogic appointmentBusinessLogic = new AppointmentBusinessLogic();
    // Handles POST requests to add a note to an appointment
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int appointmentID = Integer.parseInt(request.getParameter("appointmentID"));
        String note = request.getParameter("note");
        
        try {
            // Attempt to add the note to the specified appointment
            appointmentBusinessLogic.addNoteToAppointment(appointmentID, note);
            // Redirect to doctor dashboard or another appropriate page after successful operation
            response.sendRedirect("doctor.jsp"); // Redirect to doctor dashboard or any appropriate page
        } catch (IllegalArgumentException e) {
            // Handle specific case where input parameters might be invalid
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("doctor.jsp").forward(request, response);
        } catch (SQLException e) {
            // Handle SQL-related exceptions, indicating a database issue
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error while adding note");
            request.getRequestDispatcher("doctor.jsp").forward(request, response);
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unexpected error occurred");
            request.getRequestDispatcher("doctor.jsp").forward(request, response);
        }
    }
}

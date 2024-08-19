/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
/*
 * Servlet implementation class UpdateAppointmentServlet
 * This servlet handles the update of existing appointments. It processes 
 * form submissions for updating appointment details and invokes the 
 * appropriate business logic to perform the update.
 */

package controller;

import businesslayer.AppointmentBusinessLogic;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateAppointmentServlet")
public class UpdateAppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Instance of the business logic class for appointment management
    private AppointmentBusinessLogic appointmentBusinessLogic = new AppointmentBusinessLogic();

    // Handles POST requests to update an existing appointment
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve appointment details from request parameters
            int appointmentID = Integer.parseInt(request.getParameter("appointmentID"));
            Date appointmentDate = Date.valueOf(request.getParameter("appointmentDate"));
            Time appointmentTime = Time.valueOf(request.getParameter("appointmentTime"));
            String reason = request.getParameter("reason");

            // Use the business logic class to update the appointment in the database
            appointmentBusinessLogic.updateAppointment(appointmentID, appointmentDate, appointmentTime, reason);

            // Redirect to the patient page after a successful update
            response.sendRedirect("patient.jsp");
        } catch (IllegalArgumentException e) {
            // Handle errors related to invalid input or appointment not found
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating appointment: " + e.getMessage());
            request.getRequestDispatcher("editAppointment.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle other general exceptions
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating appointment: " + e.getMessage());
            request.getRequestDispatcher("editAppointment.jsp").forward(request, response);
        }
    }
}

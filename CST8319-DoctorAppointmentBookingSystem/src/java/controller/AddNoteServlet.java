/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int appointmentID = Integer.parseInt(request.getParameter("appointmentID"));
        String note = request.getParameter("note");
        
        try {
            appointmentBusinessLogic.addNoteToAppointment(appointmentID, note);
            response.sendRedirect("doctor.jsp"); // Redirect to doctor dashboard or any appropriate page
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("doctor.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error while adding note");
            request.getRequestDispatcher("doctor.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unexpected error occurred");
            request.getRequestDispatcher("doctor.jsp").forward(request, response);
        }
    }
}

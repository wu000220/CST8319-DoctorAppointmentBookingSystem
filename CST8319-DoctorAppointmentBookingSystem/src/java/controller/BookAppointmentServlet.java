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
import java.sql.Date;
import java.sql.Time;
import java.sql.SQLException;

@WebServlet("/BookAppointmentServlet")
public class BookAppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private AppointmentBusinessLogic appointmentBusinessLogic = new AppointmentBusinessLogic();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int doctorID = Integer.parseInt(request.getParameter("doctorID"));
            int patientID = (Integer) request.getSession().getAttribute("patientID"); // Get patient ID from session
            Date appointmentDate = Date.valueOf(request.getParameter("appointmentDate"));
            Time appointmentTime = Time.valueOf(request.getParameter("appointmentTime") + ":00");
            String reason = request.getParameter("reason");

            appointmentBusinessLogic.bookAppointment(doctorID, patientID, appointmentDate, appointmentTime, reason);
            response.sendRedirect("patient.jsp"); // Redirect to patient dashboard
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("bookTheDoctor.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error while booking appointment");
            request.getRequestDispatcher("bookTheDoctor.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unexpected error occurred");
            request.getRequestDispatcher("bookTheDoctor.jsp").forward(request, response);
        }
    }
}

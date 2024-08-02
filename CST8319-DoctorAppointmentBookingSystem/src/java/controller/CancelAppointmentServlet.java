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

@WebServlet("/CancelAppointmentServlet")
public class CancelAppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private AppointmentBusinessLogic appointmentBusinessLogic = new AppointmentBusinessLogic();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int appointmentID = Integer.parseInt(request.getParameter("appointmentID"));

            appointmentBusinessLogic.cancelAppointment(appointmentID);

            response.sendRedirect("patient.jsp"); // Redirect to patient dashboard
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("patient.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error while cancelling appointment");
            request.getRequestDispatcher("patient.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unexpected error occurred");
            request.getRequestDispatcher("patient.jsp").forward(request, response);
        }
    }
}

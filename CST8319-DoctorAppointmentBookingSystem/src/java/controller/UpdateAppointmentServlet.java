/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dataaccesslayer.AppointmentDao;
import model.Appointment;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int appointmentID = Integer.parseInt(request.getParameter("appointmentID"));
            Date appointmentDate = Date.valueOf(request.getParameter("appointmentDate"));
            Time appointmentTime = Time.valueOf(request.getParameter("appointmentTime"));
            String reason = request.getParameter("reason");

            AppointmentDao appointmentDao = new AppointmentDao();
            Appointment appointment = appointmentDao.getAppointmentByID(appointmentID);

            if (appointment != null) {
                appointment.setAppointmentDate(appointmentDate);
                appointment.setAppointmentTime(appointmentTime);
                appointment.setReason(reason);

                appointmentDao.updateAppointment(appointment);
                response.sendRedirect("patient.jsp"); // Redirect to patient page after update
            } else {
                throw new Exception("Appointment not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating appointment: " + e.getMessage());
            request.getRequestDispatcher("editAppointment.jsp").forward(request, response);
        }
    }
}

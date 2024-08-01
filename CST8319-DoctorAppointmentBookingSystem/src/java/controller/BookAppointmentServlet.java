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

@WebServlet("/BookAppointmentServlet")
public class BookAppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int doctorID = Integer.parseInt(request.getParameter("doctorID"));
            int patientID = (Integer) request.getSession().getAttribute("patientID"); // Get patient ID from session
            Date appointmentDate = Date.valueOf(request.getParameter("appointmentDate"));
            Time appointmentTime = Time.valueOf(request.getParameter("appointmentTime") + ":00");
            String reason = request.getParameter("reason");

            AppointmentDao appointmentDao = new AppointmentDao();
            Appointment appointment = new Appointment();
            appointment.setDoctorID(doctorID);
            appointment.setPatientID(patientID);
            appointment.setAppointmentDate(appointmentDate);
            appointment.setAppointmentTime(appointmentTime);
            appointment.setReason(reason);

            appointmentDao.bookAppointment(appointment); // Method to insert appointment into database

            response.sendRedirect("patient.jsp"); // Redirect to patient dashboard
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error booking appointment: " + e.getMessage());
            request.getRequestDispatcher("bookTheDoctor.jsp").forward(request, response);
        }
    }
}

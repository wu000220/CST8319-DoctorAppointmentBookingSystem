/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dataaccesslayer.AppointmentDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CancelAppointmentServlet")
public class CancelAppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int appointmentID = Integer.parseInt(request.getParameter("appointmentID"));

            AppointmentDao appointmentDao = new AppointmentDao();
            appointmentDao.deleteAppointment(appointmentID);

            response.sendRedirect("patient.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error cancelling appointment: " + e.getMessage());
            request.getRequestDispatcher("patient.jsp").forward(request, response);
        }
    }
}

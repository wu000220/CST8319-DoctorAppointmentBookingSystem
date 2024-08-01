/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dataaccesslayer.AppointmentDao;

@WebServlet("/AddNoteServlet")
public class AddNoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private AppointmentDao appointmentDao = new AppointmentDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int appointmentID = Integer.parseInt(request.getParameter("appointmentID"));
        String note = request.getParameter("note");
        
        try {
            appointmentDao.addNoteToAppointment(appointmentID, note);
            response.sendRedirect("doctor.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error adding note");
            request.getRequestDispatcher("doctor.jsp").forward(request, response);
        }
    }
}

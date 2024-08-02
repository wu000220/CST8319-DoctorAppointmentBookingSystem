/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import businesslayer.DoctorBusinessLogic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateDoctorServlet")
public class UpdateDoctorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DoctorBusinessLogic doctorBusinessLogic = new DoctorBusinessLogic();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int doctorID = Integer.parseInt(request.getParameter("doctorID"));
            String name = request.getParameter("doctorName");
            String address = request.getParameter("doctorAddress");
            String mobile = request.getParameter("doctorMobile");
            String email = request.getParameter("doctorEmail");
            String password = request.getParameter("doctorPwd");
            String specialization = request.getParameter("specialization");

            // Use the business logic class to update the doctor's profile
            doctorBusinessLogic.updateDoctor(doctorID, name, address, mobile, email, password, specialization);

            response.sendRedirect("doctor.jsp"); // Redirect to doctor dashboard
        } catch (IllegalArgumentException e) {
            // Handle validation errors
            e.printStackTrace();
            request.setAttribute("updateError", "Error updating profile: " + e.getMessage());
            request.getRequestDispatcher("viewProfileDoctor.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            request.setAttribute("updateError", "An error occurred while updating the profile.");
            request.getRequestDispatcher("viewProfileDoctor.jsp").forward(request, response);
        }
    }
}

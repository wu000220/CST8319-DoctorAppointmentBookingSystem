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

import dataaccesslayer.DoctorDao;
import model.Doctor;

@WebServlet("/UpdateDoctorServlet")
public class UpdateDoctorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DoctorDao doctorDao = new DoctorDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int doctorID = Integer.parseInt(request.getParameter("doctorID"));
        String name = request.getParameter("doctorName");
        String address = request.getParameter("doctorAddress");
        String mobile = request.getParameter("doctorMobile");
        String email = request.getParameter("doctorEmail");
        String password = request.getParameter("doctorPwd");
        String specialization = request.getParameter("specialization");

        try {
            Doctor doctor = new Doctor();
            doctor.setDoctorID(doctorID);
            doctor.setDoctorName(name);
            doctor.setDoctorAddress(address);
            doctor.setDoctorMobile(mobile);
            doctor.setDoctorEmail(email);
            doctor.setDoctorPwd(password);
            doctor.setSpecialization(specialization);
            
            doctorDao.updateDoctor(doctor);
            response.sendRedirect("doctor.jsp"); // Redirect to doctor dashboard
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("updateError", "An error occurred while updating the profile.");
            request.getRequestDispatcher("viewProfileDoctor.jsp").forward(request, response);
        }
    }
}

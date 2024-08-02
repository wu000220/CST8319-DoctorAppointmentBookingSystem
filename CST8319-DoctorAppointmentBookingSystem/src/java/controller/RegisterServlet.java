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
import dataaccesslayer.PatientDao;
import model.Doctor;
import model.Patient;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DoctorDao doctorDao = new DoctorDao();
    private PatientDao patientDao = new PatientDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String mobile = request.getParameter("mobile");

        try {
            if (role.equals("doctor")) {
                // Handle doctor registration
                Doctor doctor = new Doctor();
                doctor.setDoctorName(name);
                doctor.setDoctorEmail(email);
                doctor.setDoctorPwd(password);
                doctor.setDoctorAddress(address);
                doctor.setDoctorMobile(mobile);
                doctor.setSpecialization(""); // Default specialization

                doctorDao.registerDoctor(doctor);
            } else if (role.equals("patient")) {
                // Handle patient registration
                Patient patient = new Patient();
                patient.setPatientName(name);
                patient.setPatientEmail(email);
                patient.setPatientPwd(password);
                patient.setPatientAddress(address);
                patient.setPatientMobile(mobile);

                patientDao.registerPatient(patient);
            }
            response.sendRedirect("index.jsp"); // Redirect to login page after successful registration
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("registerError", "An error occurred while registering the profile. " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}

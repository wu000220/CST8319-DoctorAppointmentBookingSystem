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
import javax.servlet.http.HttpSession;

import dataaccesslayer.PatientDao;
import model.Patient;

@WebServlet("/UpdateProfilePatientServlet")
public class UpdatePatientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PatientDao patientDao = new PatientDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute("patient");

        if (patient == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String patientName = request.getParameter("patientName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String password = request.getParameter("password");

        patient.setPatientName(patientName);
        patient.setPatientEmail(email);
        patient.setPatientMobile(phoneNumber);
        patient.setPatientAddress(address);
        patient.setPatientPwd(password);

        try {
            patientDao.updatePatient(patient);
            session.setAttribute("patient", patient);
            response.sendRedirect("patient.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("updateError", "Error updating profile.");
            request.getRequestDispatcher("viewProfilePatient.jsp").forward(request, response);
        }
    }
}

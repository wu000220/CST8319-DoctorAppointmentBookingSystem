<%-- 
    Document   : bookTheDoctor
    Created on : Aug 1, 2024, 3:45:50 PM
    Author     : aaron
--%>

<%@ page import="dataaccesslayer.DoctorDao" %>
<%@ page import="model.Doctor" %>
<%@ page import="java.sql.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Appointment</title>
    <link rel="stylesheet" href="CSS/patient.css">
</head>
<body>
    <div class="container">
        <h1>Book an Appointment with Doctor</h1>
        <div class="buttons">
            <a href="viewProfilePatient.jsp" class="button">View Profile</a>
            
            <a href="patient.jsp" class="button">Back to Dashboard</a>
            <a href="LogoutServlet" class="button">Logout</a>
            
        </div>

        <% 
            // Retrieve the doctor ID from the request
            String doctorIDStr = request.getParameter("doctorID");
            int doctorID = Integer.parseInt(doctorIDStr);

            // Fetch doctor details from the database
            DoctorDao doctorDao = new DoctorDao();
            Doctor doctor = doctorDao.getDoctorByID(doctorID);

            if (doctor != null) {
        %>
        <h2>Doctor Information</h2>
        <p><strong>Doctor Name:</strong> <%= doctor.getDoctorName() %></p>
        <p><strong>Specialization:</strong> <%= doctor.getSpecialization() %></p>

        <h2>Appointment Details</h2>
        <form action="BookAppointmentServlet" method="post">
            <input type="hidden" name="doctorID" value="<%= doctor.getDoctorID() %>">
            <div class="form-group">
                <label for="appointmentDate">Date:</label>
                <input type="date" id="appointmentDate" name="appointmentDate" required>
            </div>
            <div class="form-group">
                <label for="appointmentTime">Time:</label>
                <input type="time" id="appointmentTime" name="appointmentTime" required>
            </div>
            <div class="form-group">
                <label for="reason">Reason:</label>
                <textarea id="reason" name="reason" rows="4" required></textarea>
            </div>
            <button type="submit" class="button">Book Appointment</button>
        </form>
        <% 
            } else {
        %>
        <p>Doctor not found.</p>
        <% 
            }
        %>
    </div>
</body>
</html>


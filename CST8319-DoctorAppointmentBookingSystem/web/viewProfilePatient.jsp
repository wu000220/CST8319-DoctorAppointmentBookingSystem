<%-- 
    Document   : viewProfilePatient
    Created on : Aug 1, 2024, 1:54:31 PM
    Author     : aaron
--%>

<%@ page import="model.Patient" %>
<%@ page import="dataaccesslayer.PatientDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Profile</title>
    <link rel="stylesheet" href="CSS/patient.css">
</head>
<body>
    <div class="container">
        <h1>Profile</h1>
        <div class="buttons">
            <a href="viewProfilePatient.jsp" class="button">View Profile</a>
            <a href="bookAppointment.jsp" class="button">Book Appointment</a>
            <a href="patient.jsp" class="button">Back to Dashboard</a>
            <a href="LogoutServlet" class="button">Logout</a>
            
        </div>
        
        <%
            Patient patient = (Patient) session.getAttribute("patient");
            if (patient == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <form action="updateProfilePatientServlet" method="post">
            <div class="form-group">
                <label for="patientName">Name:</label>
                <input type="text" id="patientName" name="patientName" value="<%= patient.getPatientName() %>" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="<%= patient.getPatientEmail() %>" required>
            </div>
            <div class="form-group">
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" id="phoneNumber" name="phoneNumber" value="<%= patient.getPatientMobile() %>" required>
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="<%= patient.getPatientAddress() %>" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="<%= patient.getPatientPwd() %>" required>
            </div>
            <button type="submit">Update Profile</button>
        </form>
        
        <div class="buttons">
            <a href="patient.jsp" class="button">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>

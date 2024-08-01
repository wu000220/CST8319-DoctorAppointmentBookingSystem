<%-- 
    Document   : ViewProfile
    Created on : Jul 31, 2024, 8:56:12 PM
    Author     : aaron
--%>

<%@ page import="model.Doctor" %>
<%@ page import="dataaccesslayer.DoctorDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Profile</title>
    <link rel="stylesheet" href="CSS/doctor.css">
</head>
<body>
    <div class="container">
        <h1>Doctor Profile</h1>
        <div class="buttons">
            <a href="viewProfileDoctor.jsp" class="button">View Profile</a>
            <a href="viewHistoryDoctor.jsp" class="button">View Appointment History</a>
            <a href="LogoutServlet" class="button">Logout</a>
        </div>
        
        <%
            try {
                // Retrieve doctor's ID from the session
                Integer doctorID = (Integer) session.getAttribute("doctorID");
                if (doctorID == null) {
                    throw new Exception("Doctor ID is not set in session");
                }
                
                // Fetch doctor details from database
                DoctorDao doctorDao = new DoctorDao();
                Doctor doctor = doctorDao.getDoctorByID(doctorID);
                
                if (doctor == null) {
                    throw new Exception("Doctor not found");
                }
        %>
        
        <form action="updateDoctorServlet" method="post">
            <input type="hidden" name="doctorID" value="<%= doctor.getDoctorID() %>">
            <div class="form-group">
                <label for="doctorName">Name:</label>
                <input type="text" id="doctorName" name="doctorName" value="<%= doctor.getDoctorName() %>" required>
            </div>
            <div class="form-group">
                <label for="doctorAddress">Address:</label>
                <input type="text" id="doctorAddress" name="doctorAddress" value="<%= doctor.getDoctorAddress() %>">
            </div>
            <div class="form-group">
                <label for="doctorMobile">Mobile:</label>
                <input type="text" id="doctorMobile" name="doctorMobile" value="<%= doctor.getDoctorMobile() %>">
            </div>
            <div class="form-group">
                <label for="doctorEmail">Email:</label>
                <input type="email" id="doctorEmail" name="doctorEmail" value="<%= doctor.getDoctorEmail() %>" required>
            </div>
            <div class="form-group">
                <label for="doctorPwd">Password:</label>
                <input type="password" id="doctorPwd" name="doctorPwd" value="<%= doctor.getDoctorPwd() %>" required>
            </div>
            <button type="submit">Update Profile</button>
        </form>

        <a href="doctor.jsp" class="button">Back to Dashboard</a>

        <% 
            } catch (Exception e) {
                e.printStackTrace();
        %>
        <p>Error: <%= e.getMessage() %></p>
        <% 
            }
        %>
    </div>
</body>
</html>


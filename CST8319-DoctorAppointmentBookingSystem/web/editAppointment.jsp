<%-- 
    Document   : editAppointment
    Created on : Aug 1, 2024, 2:24:35 PM
    Author     : aaron
--%>

<%@ page import="dataaccesslayer.AppointmentDao" %>
<%@ page import="model.Appointment" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Appointment</title>
    <link rel="stylesheet" href="CSS/patient.css">
</head>
<body>
    <div class="container">
        <h1>Edit Appointment</h1>
        
        <% 
            try {
                AppointmentDao appointmentDao = new AppointmentDao();
                Integer appointmentID = Integer.parseInt(request.getParameter("appointmentID"));

                if (appointmentID == null) {
                    throw new Exception("Appointment ID is missing");
                }

                Appointment appointment = appointmentDao.getAppointmentByID(appointmentID);

                if (appointment == null) {
                    throw new Exception("Appointment not found");
                }
        %>
        <form action="UpdateAppointmentServlet" method="post">
            <input type="hidden" name="appointmentID" value="<%= appointment.getAppointmentID() %>">
            <div class="form-group">
                <label for="doctorName">Doctor Name:</label>
                <input type="text" id="doctorName" name="doctorName" value="<%= appointment.getDoctorName() %>" readonly>
            </div>
            <div class="form-group">
                <label for="appointmentDate">Date:</label>
                <input type="date" id="appointmentDate" name="appointmentDate" value="<%= appointment.getAppointmentDate() %>" required>
            </div>
            <div class="form-group">
                <label for="appointmentTime">Time:</label>
                <input type="time" id="appointmentTime" name="appointmentTime" value="<%= appointment.getAppointmentTime() %>" required>
            </div>
            <div class="form-group">
                <label for="reason">Reason:</label>
                <textarea id="reason" name="reason" rows="4" required><%= appointment.getReason() %></textarea>
            </div>
            <button type="submit" class="button">Update Appointment</button>
            <a href="patient.jsp" class="button">Back to Dashboard</a>
        </form>
        <% 
            } catch (Exception e) {
                e.printStackTrace(); // Print stack trace to server logs
        %>
        <div class="error-message">Error fetching appointment: <%= e.getMessage() %></div>
        <a href="patient.jsp" class="button">Back to Dashboard</a>
        <% 
            }
        %>
    </div>
</body>
</html>

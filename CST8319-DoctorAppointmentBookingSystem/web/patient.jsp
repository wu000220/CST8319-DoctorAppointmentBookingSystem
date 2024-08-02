<%-- 
    Document   : patient
    Created on : Jul 31, 2024, 8:31:56 PM
    Author     : aaron
--%>

<%-- 
    Document   : patient
    Created on : Jul 31, 2024, 8:31:56 PM
    Author     : aaron
--%>

<%@ page import="dataaccesslayer.AppointmentDao" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Appointment" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patient Dashboard</title>
    <link rel="stylesheet" href="CSS/patient.css">
</head>
<body>
    <div class="container">
        <h1>Welcome, <%= session.getAttribute("patientName") %></h1>

        <div class="buttons">
            <a href="viewProfilePatient.jsp" class="button">View Profile</a>
            <a href="bookAppointment.jsp" class="button">Book Appointment</a>
            <a href="viewHistoryPatient.jsp" class="button">View Appointment History</a>
            <a href="LogoutServlet" class="button">Logout</a>
        </div>

        <h2>Upcoming Appointments</h2>
        <table class="appointments-table">
            <thead>
                <tr>
                    <th>Appointment ID</th>
                    <th>Doctor Name</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Reason</th>
                    <th>Doctor Note</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    try {
                        // Fetch upcoming appointments from the database
                        AppointmentDao appointmentDao = new AppointmentDao();
                        Integer patientID = (Integer) session.getAttribute("patientID");

                        if (patientID == null) {
                            throw new Exception("Patient ID is not set in session");
                        }

                        List<Appointment> appointments = appointmentDao.getUpcomingAppointmentsByPatientID(patientID);

                        if (appointments != null && !appointments.isEmpty()) {
                            for (Appointment appointment : appointments) {
                %>
                <tr>
                    <td><%= appointment.getAppointmentID() %></td>
                    <td><%= appointment.getDoctorName() %></td>
                    <td><%= appointment.getAppointmentDate() %></td>
                    <td><%= appointment.getAppointmentTime() %></td>
                    <td><%= appointment.getReason() %></td>
                    <td><%= appointment.getNote() %></td>
                    <td>
                        <a href="editAppointment.jsp?appointmentID=<%= appointment.getAppointmentID() %>" class="button">Edit</a>
                        <a href="CancelAppointmentServlet?appointmentID=<%= appointment.getAppointmentID() %>" class="button cancel-button">Cancel</a>
                    </td>
                </tr>
                <% 
                            }
                        } else {
                %>
                <tr>
                    <td colspan="7">No upcoming appointments</td>
                </tr>
                <% 
                        }
                    } catch (Exception e) {
                        e.printStackTrace(); // Print stack trace to server logs
                %>
                <tr>
                    <td colspan="7">Error fetching appointments: <%= e.getMessage() %></td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>

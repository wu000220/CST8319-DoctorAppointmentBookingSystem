<%-- 
    Document   : viewHistoryDoctor
    Created on : Jul 31, 2024, 9:30:02 PM
    Author     : aaron
--%>

<%@ page import="java.util.List" %>
<%@ page import="model.Appointment" %>
<%@ page import="dataaccesslayer.AppointmentDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Appointment History</title>
    <link rel="stylesheet" href="CSS/doctor.css">
</head>
<body>
    <div class="container">
        <h1>Appointment History for <%= session.getAttribute("doctorName") %></h1>
        
        <div class="buttons">
            <a href="viewProfileDoctor.jsp" class="button">View Profile</a>
            <a href="doctor.jsp" class="button">Back to Dashboard</a>
            <a href="LogoutServlet" class="button">Logout</a>
        </div>

        <h2>Past Appointments</h2>
        <table class="appointments-table">
            <thead>
                <tr>
                    <th>Appointment ID</th>
                    <th>Patient Name</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Reason</th>
                    <th>Note</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    try {
                        // Fetch past appointments from the database
                        AppointmentDao appointmentDao = new AppointmentDao();
                        Integer doctorID = (Integer) session.getAttribute("doctorID");
                        List<Appointment> appointments = appointmentDao.getPastAppointmentsByDoctorID(doctorID);

                        if (appointments != null && !appointments.isEmpty()) {
                            for (Appointment appointment : appointments) {
                %>
                <tr>
                    <td><%= appointment.getAppointmentID() %></td>
                    <td><%= appointment.getPatientName() %></td>
                    <td><%= appointment.getAppointmentDate() %></td>
                    <td><%= appointment.getAppointmentTime() %></td>
                    <td><%= appointment.getReason() %></td>
                    <td><%= appointment.getNote() %></td>
                </tr>
                <% 
                            }
                        } else {
                %>
                <tr>
                    <td colspan="5">No past appointments</td>
                </tr>
                <% 
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                %>
                <tr>
                    <td colspan="5">Error fetching appointments</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>

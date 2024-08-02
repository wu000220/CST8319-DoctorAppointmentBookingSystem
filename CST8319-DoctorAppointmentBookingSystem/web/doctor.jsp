<%-- 
    Document   : doctor
    Created on : Jul 31, 2024, 7:44:43 PM
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
    <title>Doctor Dashboard</title>
    <link rel="stylesheet" href="CSS/doctor.css">
    <script src="JS/doctor.js" defer></script>
</head>
<body>
    <div class="container">
        <h1>Welcome, <%= session.getAttribute("doctorName") %></h1>

        <div class="buttons">
            <a href="viewProfileDoctor.jsp" class="button">View Profile</a>
            <a href="viewHistoryDoctor.jsp" class="button">View Appointment History</a>
            <a href="LogoutServlet" class="button">Logout</a>
        </div>

        <h2>Upcoming Appointments</h2>
        <table class="appointments-table">
            <thead>
                <tr>
                    <th>Appointment ID</th>
                    <th>Patient Name</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Reason</th>
                    <th>Note</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    try {
                        // Fetch upcoming appointments from the database
                        AppointmentDao appointmentDao = new AppointmentDao();
                        Integer doctorID = (Integer) session.getAttribute("doctorID");

                        if (doctorID == null) {
                            throw new Exception("Doctor ID is not set in session");
                        }

                        List<Appointment> appointments = appointmentDao.getUpcomingAppointmentsByDoctorID(doctorID);

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
                    <td>
                        <button class="note-button" data-id="<%= appointment.getAppointmentID() %>">Add Note</button>
                    </td>
                </tr>
                <% 
                            }
                        } else {
                %>
                <tr>
                    <td colspan="6">No upcoming appointments</td>
                </tr>
                <% 
                        }
                    } catch (Exception e) {
                        e.printStackTrace(); // Print stack trace to server logs
                %>
                <tr>
                    <td colspan="6">Error fetching appointments: <%= e.getMessage() %></td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>

    <!-- Modal for Adding Note -->
    <div id="noteModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>Add Note</h2>
            <form id="noteForm" action="addNoteServlet" method="post">
                <input type="hidden" id="appointmentID" name="appointmentID">
                <div class="form-group">
                    <label for="note">Note:</label>
                    <textarea id="note" name="note" rows="4" required></textarea>
                </div>
                <button type="submit">Save Note</button>
            </form>
        </div>
    </div>
</body>
</html>

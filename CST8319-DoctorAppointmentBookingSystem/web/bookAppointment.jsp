<%-- 
    Document   : bookAppointment
    Created on : Aug 1, 2024, 3:33:26 PM
    Author     : aaron
--%>

<%@ page import="dataaccesslayer.DoctorDao" %>
<%@ page import="model.Doctor" %>
<%@ page import="java.util.List" %>
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
        <h1>Book an Appointment</h1>
        <div class="buttons">
            <a href="viewProfilePatient.jsp" class="button">View Profile</a>
            <a href="bookAppointment.jsp" class="button">Book Appointment</a>
            <a href="patient.jsp" class="button">Back to Dashboard</a>
            <a href="LogoutServlet" class="button">Logout</a>
            
        </div>

        <table class="doctors-table">
            <thead>
                <tr>
                    <th>Doctor ID</th>
                    <th>Doctor Name</th>
                    <th>Specialization</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    try {
                        // Fetch list of doctors from the database
                        DoctorDao doctorDao = new DoctorDao();
                        List<Doctor> doctors = doctorDao.getAllDoctors();

                        if (doctors != null && !doctors.isEmpty()) {
                            for (Doctor doctor : doctors) {
                %>
                <tr>
                    <td><%= doctor.getDoctorID() %></td>
                    <td><%= doctor.getDoctorName() %></td>
                    <td><%= doctor.getSpecialization() %></td>
                    <td>
                        <form action="BookAppointmentServlet" method="post">
                            <input type="hidden" name="doctorID" value="<%= doctor.getDoctorID() %>">
                            <button type="submit" class="button">Book</button>
                        </form>
                    </td>
                </tr>
                <% 
                            }
                        } else {
                %>
                <tr>
                    <td colspan="4">No doctors available</td>
                </tr>
                <% 
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                %>
                <tr>
                    <td colspan="4">Error fetching doctors: <%= e.getMessage() %></td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>

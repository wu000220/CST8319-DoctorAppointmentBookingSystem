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
           <style>
        .profile-form {
            width: 100%;
            max-width: 600px; 
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); 
            margin: 20px auto; 
        }

        .profile-form .form-group {
            display: grid;
            grid-template-columns: 150px 1fr; 
            gap: 10px;
            margin-bottom: 15px;
        }

        .profile-form .form-group label {
            font-size: 16px; 
            color: #333; 
            align-self: center; 
        }

        .profile-form .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px; 
        }

        .form-actions {
            display: flex;
            justify-content: center; 
            margin-top: 20px;
        }

        .form-actions button {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
        }

        .form-actions button:hover {
            background-color: #0056b3;
        }

        .buttons {
            text-align: center; 
            margin-top: 20px;
        }

        .buttons .button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 6px;
            border: 2px solid #0056b3;
            font-size: 16px;
        }

        .buttons .button:hover {
            background-color: #0056b3;
            border-color: #004494;
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome, <%= session.getAttribute("patientName") %></h1>
    </header>
    <main>
        <h2>Edit Appointment</h2><br>         
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
        <form action="UpdateAppointmentServlet" method="post" class="profile-form">
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
        </form>
        <div class="buttons">
            <a href="patient.jsp" class="button">Back to Dashboard</a>
        </div>
        <% 
            } catch (Exception e) {
                e.printStackTrace(); // Print stack trace to server logs
        %>
        <div class="error-message">Error fetching appointment: <%= e.getMessage() %></div>
        <a href="patient.jsp" class="button">Back to Dashboard</a>
        <% 
            }
        %>
    </main>
</body>
</html>

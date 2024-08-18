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
    <nav class="navigation">
             <a href="viewProfilePatient.jsp" class="button">View Profile</a>
            <a href="bookAppointment.jsp" class="button">Book Appointment</a>
            <a href="patient.jsp" class="button">Back to Dashboard</a>
            <a href="LogoutServlet" class="button">Logout</a>
    </nav>
    <main>
        <h2>Patient Profile</h2><br>    
        <%
            Patient patient = (Patient) session.getAttribute("patient");
            if (patient == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <form action="UpdateProfilePatientServlet" method="post" class="profile-form">
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
    </main>
</body>
</html>

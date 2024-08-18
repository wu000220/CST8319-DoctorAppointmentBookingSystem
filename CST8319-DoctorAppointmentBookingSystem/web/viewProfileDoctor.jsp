<%-- 
    Document   : ViewProfile
    Created on : Jul 31, 2024, 8:56:12 PM
    Author     : aaron
--%>
<%-- 
    Document   : viewProfileDoctor
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
        <h1>Welcome, <%= session.getAttribute("doctorName") %></h1>
    </header>
    <nav class="navigation">
        <a href="viewProfileDoctor.jsp" class="button">View Profile</a>
        <a href="viewHistoryDoctor.jsp" class="button">View Appointment History</a>
        <a href="LogoutServlet" class="button">Logout</a>
    </nav>
    <main>
        <h2>Doctor Profile</h2>      
        <%
            try {
                Integer doctorID = (Integer) session.getAttribute("doctorID");
                if (doctorID == null) {
                    throw new Exception("Doctor ID is not set in session");
                }
                
                DoctorDao doctorDao = new DoctorDao();
                Doctor doctor = doctorDao.getDoctorByID(doctorID);
                
                if (doctor == null) {
                    throw new Exception("Doctor not found");
                }
        %>        
        <form action="UpdateDoctorServlet" method="post" class="profile-form">
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
            <div class="form-group">
                <label for="specialization">Specialization:</label>
                <input type="text" id="specialization" name="specialization" value="<%= doctor.getSpecialization() %>">
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
    </main>
</body>
</html>

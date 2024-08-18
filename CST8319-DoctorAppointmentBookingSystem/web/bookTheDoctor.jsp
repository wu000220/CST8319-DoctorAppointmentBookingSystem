
<%@ page import="dataaccesslayer.DoctorDao" %>
<%@ page import="model.Doctor" %>
<%@ page import="java.sql.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Appointment</title>
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

        .form-group {
            display: grid;
            grid-template-columns: 150px 1fr; 
            gap: 10px;
            margin-bottom: 15px;
        }

        .form-group label {
            font-size: 16px; 
            color: #333; 
            align-self: center; 
        }

        .form-group input, .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px; 
        }
        
        .form-header {
            text-align: center; 
            margin-bottom: 20px;
        }

        .form-actions {
            text-align: center; 
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

        .center-content {
            margin-bottom: 20px;
            color: black;
        }
        
        .button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 6px;
            border: 2px solid #0056b3;
            font-size: 16px;
        }

        .button:hover {
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
        <a href="patient.jsp" class="button">Back to Dashboard</a>
        <a href="LogoutServlet" class="button">Logout</a>
    </nav>
    <main>
        <h2>Book an Appointment with Doctor</h2><br> 
        <% 
            String doctorIDStr = request.getParameter("doctorID");
            int doctorID = Integer.parseInt(doctorIDStr);

            DoctorDao doctorDao = new DoctorDao();
            Doctor doctor = doctorDao.getDoctorByID(doctorID);

            if (doctor != null) {
        %>
        <form action="BookAppointmentServlet" method="post" class="profile-form">
            <div class="center-content">
            <h3><strong>Doctor Name:</strong> <%= doctor.getDoctorName() %></h3>
            <h3><strong>Specialization:</strong> <%= doctor.getSpecialization() %></h3>
            </div>
            <input type="hidden" name="doctorID" value="<%= doctor.getDoctorID() %>">
            <div class="form-group">
                <label for="appointmentDate">Date:</label>
                <input type="date" id="appointmentDate" name="appointmentDate" required>
            </div>
            <div class="form-group">
                <label for="appointmentTime">Time:</label>
                <input type="time" id="appointmentTime" name="appointmentTime" required>
            </div>
            <div class="form-group">
                <label for="reason">Reason:</label>
                <textarea id="reason" name="reason" rows="4" required></textarea>
            </div>
            <div class="form-actions">
            <button type="submit" class="button">Book Appointment</button>
            </div>
        </form>
        <% 
            } else {
        %>
        <p>Doctor not found.</p>
        <% 
            }
        %>
    </main>
</body>
</html>


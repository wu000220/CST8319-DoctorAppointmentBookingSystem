<%-- 
    Document   : Register
    Created on : Jul 31, 2024, 6:43:15 PM
    Author     : aaron
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Page</title>
    <link rel="stylesheet" href="CSS/register.css">
</head>
<body>
    <div class="container">
        <h1>Register</h1>
        <form action="RegisterServlet" method="post">
            <div class="form-group">
                <label for="role">Register as:</label>
                <select id="role" name="role" required>
                    <option value="doctor">Doctor</option>
                    <option value="patient">Patient</option>
                </select>
            </div>
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required>
            </div>
            <div class="form-group">
                <label for="mobile">Mobile:</label>
                <input type="text" id="mobile" name="mobile" required>
            </div>
            <button type="submit">Register</button>
        </form>
        <p>Already have an account? <a href="index.jsp">Login here</a></p>
    </div>
</body>
</html>

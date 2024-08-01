/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
DROP DATABASE IF EXISTS bookingsystem;
CREATE DATABASE bookingsystem;
USE bookingsystem;

drop table if exists Appointment;
drop table if exists Doctor;
drop table if exists Patient;

-- Create Doctor table
CREATE TABLE Doctor (
    doctorID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    doctorName VARCHAR(100) NOT NULL,
    doctorAddress VARCHAR(255),
    doctorMobile VARCHAR(15),
    doctorEmail VARCHAR(100),
    doctorPwd VARCHAR(100) NOT NULL,
    specialization VARCHAR(100)
);

-- Create Patient table
CREATE TABLE Patient (
    patientID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    patientName VARCHAR(100) NOT NULL,
    patientAddress VARCHAR(255),
    patientMobile VARCHAR(15),
    patientEmail VARCHAR(100),
    patientPwd VARCHAR(100) NOT NULL
);

-- Create Appointment table
CREATE TABLE Appointment (
    appointmentID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    doctorID INT NOT NULL,
    patientID INT NOT NULL,
    appointmentDate DATE NOT NULL,
    appointmentTime TIME NOT NULL,
    reason VARCHAR(255),
    note TEXT,
    FOREIGN KEY (doctorID) REFERENCES Doctor(doctorID),
    FOREIGN KEY (patientID) REFERENCES Patient(patientID)
);

-- Insert sample data into Doctor table
INSERT INTO Doctor (doctorName, doctorAddress, doctorMobile, doctorEmail, doctorPwd, specialization) VALUES
('Dr. John Doe', '123 Main St, Anytown', '123-456-7890', 'johndoe@example.com', 'password123', null),
('Dr. Jane Smith', '456 Elm St, Othertown', '987-654-3210', 'janesmith@example.com', 'password456', null);

-- Insert sample data into Patient table
INSERT INTO Patient (patientName, patientAddress, patientMobile, patientEmail, patientPwd) VALUES
('Alice Johnson', '789 Oak St, Sometown', '555-123-4567', 'alice@example.com', 'password789'),
('Bob Brown', '321 Pine St, Anycity', '555-765-4321', 'bob@example.com', 'password101');

-- Insert sample data into Appointment table
INSERT INTO Appointment (doctorID, patientID, appointmentDate, appointmentTime, reason, note) VALUES
(1, 1, '2023-07-01', '09:00:00', 'Routine Checkup', 'Patient in good health.'),
(1, 1, '2025-07-01', '09:00:00', 'Routine Checkup', 'Patient in good health.'),
(2, 2, '2025-07-02', '10:00:00', 'Consultation', 'Follow-up in one week.'),
(2, 2, '2023-07-02', '10:00:00', 'Consultation', 'Follow-up in one week.');
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import model.Doctor;
import java.sql.SQLException;
import java.util.List;

public interface DoctorDaoInterface {

    Doctor getDoctorByID(int doctorID) throws SQLException;

    void updateDoctor(Doctor doctor) throws SQLException;

    List<Doctor> getAllDoctors() throws SQLException;

    void registerDoctor(Doctor doctor) throws SQLException;
}

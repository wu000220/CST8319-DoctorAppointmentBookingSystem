/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dataaccesslayer;

import model.Appointment;
import java.util.List;
import model.Doctor;

/**
 *
 * @author fwu
 */
public interface DoctorDao {

    // used for registration, add object to database
    public void addDoctor(Doctor doctor);

    // used for validate doctor login
    public Doctor validateDoctor(String email, String password);
    
    // achieve doctor detail by ID
    public Doctor getDoctorByID(int doctorID);

    // update doctor information
    public void updateDoctor(Doctor doctor);
}

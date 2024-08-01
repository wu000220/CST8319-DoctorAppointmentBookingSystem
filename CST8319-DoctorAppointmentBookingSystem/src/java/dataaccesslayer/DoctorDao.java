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
    
    // used for load all doctors
    public List<Doctor> getAllDoctor();

    // used for registration, add object to database
    public void addDoctor(Doctor doctor);

    // used for validate doctor login
    public String getDoctorPasswordByEmail(String email);
    
    // achieve doctor detail by ID
    public Doctor getDoctorByID(int doctorID);

    // update doctor information
    public Doctor updateDoctor(Integer id, String name, String address, String mobile, String email, String password);
}

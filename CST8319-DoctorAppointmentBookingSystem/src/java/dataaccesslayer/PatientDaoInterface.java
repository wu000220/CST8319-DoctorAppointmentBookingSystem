/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import model.Patient;
import java.sql.SQLException;

public interface PatientDaoInterface {

    void updatePatient(Patient patient) throws SQLException;

    Patient getPatientByID(int patientID) throws SQLException;

    void registerPatient(Patient patient) throws SQLException;
}

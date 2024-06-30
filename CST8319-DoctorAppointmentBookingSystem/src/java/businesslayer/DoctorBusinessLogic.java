/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import dataaccesslayer.DoctorDao;
import dataaccesslayer.DoctorDaoImpl;

/**
 *
 * @author fwu
 */
public class DoctorBusinessLogic {
    private DoctorDao doctorDao = null;
    
    public DoctorBusinessLogic(){
        doctorDao = new DoctorDaoImpl();
    }
}

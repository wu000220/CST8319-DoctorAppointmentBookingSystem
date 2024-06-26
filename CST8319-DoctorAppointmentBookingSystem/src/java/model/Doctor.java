/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author fwu
 */
public class Doctor {
    private Integer doctorID;
    private String doctorName;
    private String doctorAddress;
    private String doctorMobile;
    private String doctorEmail;
    private String doctorPwd;

    public Doctor() {
    }

    public Doctor(Integer doctorID, String doctorName, String doctorAddress, String doctorMobile, String doctorEmail, String doctorPwd) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.doctorAddress = doctorAddress;
        this.doctorMobile = doctorMobile;
        this.doctorEmail = doctorEmail;
        this.doctorPwd = doctorPwd;
    }

    public Integer getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Integer doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorAddress() {
        return doctorAddress;
    }

    public void setDoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
    }

    public String getDoctorMobile() {
        return doctorMobile;
    }

    public void setDoctorMobile(String doctorMobile) {
        this.doctorMobile = doctorMobile;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorPwd() {
        return doctorPwd;
    }

    public void setDoctorPwd(String doctorPwd) {
        this.doctorPwd = doctorPwd;
    }
    
    
}

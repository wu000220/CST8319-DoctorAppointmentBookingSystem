/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author fwu
 */
public class Patient {
    private Integer patientID;
    private String patientName;
    private String patientAddress;
    private String patientMobile;
    private String patientEmail;
    private String patientPwd;

    public Patient() {
    }

    public Patient(Integer patientID, String patientName, String patientAddress, String patientMobile, String patientEmail, String patientPwd) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.patientAddress = patientAddress;
        this.patientMobile = patientMobile;
        this.patientEmail = patientEmail;
        this.patientPwd = patientPwd;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public void setPatientID(Integer patientID) {
        this.patientID = patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientMobile() {
        return patientMobile;
    }

    public void setPatientMobile(String patientMobile) {
        this.patientMobile = patientMobile;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientPwd() {
        return patientPwd;
    }

    public void setPatientPwd(String patientPwd) {
        this.patientPwd = patientPwd;
    }
    
    
}

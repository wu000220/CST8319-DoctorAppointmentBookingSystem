/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * JavaBean for Doctor object.
 * @author fwu
 */
public class Doctor extends User {

    public Doctor(int id, String name, String address, String mobile, String email, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }
}

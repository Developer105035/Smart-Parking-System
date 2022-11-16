package com.example.test2.classes;

import android.widget.TextView;

public class User{
    public String fullName;
    String Email;
    String Age;
    String PhoneNumber;
    String LicenseNo;
    String Address;
    String Password;

    public User() {
    }

    public User(String fullName, String email, String age, String phoneNumber, String licenseNo, String address, String password) {
        fullName = fullName;
        Email = email;
        Age = age;
        PhoneNumber = phoneNumber;
        LicenseNo = licenseNo;
        Address = address;
        Password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        fullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getLicenseNo() {
        return LicenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        LicenseNo = licenseNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
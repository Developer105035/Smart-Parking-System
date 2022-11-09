package com.example.test2;

public class User {

    public String FullName, EmailID, Age, PhoneNumber, LicenseNo,Address, Password;

    public User(String fullName, String age, String phoneNumber, String licenseNo, String address, String password) {

    }

    public User( String fullName, String EmailID, String Age, String PhoneNumber,String LicenseNo,String Address, String Password){

        this.FullName = fullName;
        this.EmailID = EmailID;
        this.Age = Age;
        this.PhoneNumber = PhoneNumber;
        this.LicenseNo = LicenseNo;
        this.Address = Address;
        this.Password = Password;


    }
}

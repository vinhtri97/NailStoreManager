package com.example.letra.nailstoremanagement.Database;

public class Employee {
    private String firstName;
    private String phone;
    private String avatar_src;

    public Employee(String firstName, String phone, String avatar_src) {
        this.firstName = firstName;
        this.phone = phone;
        this.avatar_src = avatar_src;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatar_src() {
        return avatar_src;
    }
}

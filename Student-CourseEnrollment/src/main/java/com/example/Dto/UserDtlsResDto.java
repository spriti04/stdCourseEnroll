package com.example.Dto;

import com.example.Model.Address;

public class UserDtlsResDto {

    private int id;
    private String name;
    private String phNo;
    private String email;
    private String role;
    private Address address;
    private String courseTitle;

    public UserDtlsResDto(int id, String name, String phNo, String email, String role, Address address, String courseTitle) {
        this.id = id;
        this.name = name;
        this.phNo = phNo;
        this.email = email;
        this.role = role;
        this.address = address;
        this.courseTitle = courseTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}

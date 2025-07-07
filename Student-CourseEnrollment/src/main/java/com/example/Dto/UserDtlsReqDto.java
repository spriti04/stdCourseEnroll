package com.example.Dto;

import com.example.Model.Address;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class UserDtlsReqDto {

    private String name;
    private String phNo;
    private String email;
    private String role;
    private String password;
    private Address address;
    private Integer courseId;

    public UserDtlsReqDto(String name, String phNo, String email, String role, String password, Address address, Integer courseId) {
        this.name = name;
        this.phNo = phNo;
        this.email = email;
        this.role = role;
        this.password = password;
        this.address = address;
        this.courseId = courseId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}

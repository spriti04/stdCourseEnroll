package com.example.Dto;

public class CourseUpdateReq {

    private String title;
    private String description;
    private double fees;

    public CourseUpdateReq(String title, String description, double fees) {
        this.title = title;
        this.description = description;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }
}

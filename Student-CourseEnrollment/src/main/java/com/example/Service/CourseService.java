package com.example.Service;

import com.example.Dto.CourseUpdateReq;
import com.example.Model.Courses;

import java.util.List;

public interface CourseService {

    public Courses addNewCourse(Courses course);

    public List<Courses> allCourses();

    public String updateCourse(int id, double fees);

    public String removeCourse(int id);
}

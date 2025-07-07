package com.example.Service;

import com.example.Model.Courses;
import com.example.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Courses addNewCourse(Courses course) {
        Courses courses = courseRepository.save(course);

        return courses;
    }

    @Override
    public List<Courses> allCourses() {
        return courseRepository.findAll();
    }

    @Override
    public String updateCourse(int id, double fees) {
        Courses courses = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));

        courses.setFees(fees);

        courseRepository.save(courses);

        return "Course updated successfully";
    }

    @Override
    public String removeCourse(int id) {
        Courses courses =  courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));

        courseRepository.delete(courses);

        return "Course deleted successfully.";
    }
}

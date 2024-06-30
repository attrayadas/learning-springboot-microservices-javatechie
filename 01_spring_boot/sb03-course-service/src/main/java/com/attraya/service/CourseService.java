package com.attraya.service;

import com.attraya.dto.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CourseService {

    private List<Course> courseDatabase = new ArrayList<>();

    // create a course object in DB
    public Course onBoardNewCourse(Course course){
        course.setCourseId(new Random().nextInt(3756));
        courseDatabase.add(course);
        return course;
    }

    // load all the course from Database
    public List<Course> viewAllCourses(){
        return courseDatabase;
    }

    // filter course by course id
    public Course findByCourseId(Integer courseId){
        return courseDatabase.stream()
                .filter(course->course.getCourseId()==courseId)
                .findFirst().orElse(null);
    }

    // delete course
    public void deleteCourse(int courseId){
        Course course = findByCourseId(courseId);
        courseDatabase.remove(course);
    }

    // update the course
    public Course updateCourse(int courseId, Course course){
        Course existingCourse = findByCourseId(courseId);
        courseDatabase.set(courseDatabase.indexOf(existingCourse), course);
        return course;
    }
}

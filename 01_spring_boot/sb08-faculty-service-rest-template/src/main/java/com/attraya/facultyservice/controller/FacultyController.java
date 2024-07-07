package com.attraya.facultyservice.controller;

import com.attraya.facultyservice.dto.CourseRequestDTO;
import com.attraya.facultyservice.dto.CourseResponseDTO;
import com.attraya.facultyservice.dto.ServiceResponse;
import com.attraya.facultyservice.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/faculty-service")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PostMapping("/addNewCourse")
    public ServiceResponse<CourseResponseDTO> addNewCourse(@RequestBody CourseRequestDTO courseRequestDTO){
        return facultyService.addNewCourseToDashboard(courseRequestDTO);
    }

    @GetMapping("/allCourses")
    public ServiceResponse<List<CourseResponseDTO>> viewAllCourses(){
        return facultyService.fetchAllCourses();
    }

    @GetMapping("/getCourse/{id}")
    public ServiceResponse<CourseResponseDTO> getCourseById(@PathVariable Integer id){
        return facultyService.findCourseById(id);
    }

    @GetMapping("getCourse/request")
    public ServiceResponse<CourseResponseDTO> getCourseByRequestParam(@RequestParam(required = false) Integer courseId){
        return  facultyService.findCourseByIdUsingRequestParam(courseId);
    }

    @PutMapping("/updateCourse/{courseId}")
    public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable int courseId, @RequestBody CourseRequestDTO requestDTO){
        facultyService.updateCourseInDashboard(courseId, requestDTO);
        return facultyService.findCourseById(courseId);
    }

    @DeleteMapping("/delete/{courseId}")
    public ServiceResponse<String> deleteCourse(@PathVariable int courseId){
        facultyService.deleteCourseFromDashboard(courseId);
        return new ServiceResponse<>(HttpStatus.OK, "Course deleted successfully with id "+courseId);
    }
}

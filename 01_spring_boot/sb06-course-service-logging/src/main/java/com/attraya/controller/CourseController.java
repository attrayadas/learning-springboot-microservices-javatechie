package com.attraya.controller;

import com.attraya.dto.CourseRequestDTO;
import com.attraya.dto.CourseResponseDTO;
import com.attraya.dto.ServiceResponse;
import com.attraya.service.CourseService;
import com.attraya.util.AppUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/course")
public class CourseController {

    Logger log = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @RequestMapping(method = RequestMethod.POST)
    public ServiceResponse<CourseResponseDTO> addCourse(@RequestBody @Valid CourseRequestDTO courseRequestDTO) {
        log.info("CourseController::addCourse request payload: {}", AppUtils.convertObjectToJson(courseRequestDTO));
        ServiceResponse<CourseResponseDTO> serviceResponse = new ServiceResponse<>();
        CourseResponseDTO newCourse = courseService.onBoardNewCourse(courseRequestDTO);
        serviceResponse.setStatus(HttpStatus.OK);
        serviceResponse.setResponse(newCourse);
        log.info("CourseController::addCourse response: {}", AppUtils.convertObjectToJson(serviceResponse));
        return serviceResponse;
    }

    @GetMapping
    public ServiceResponse<List<CourseResponseDTO>> findAllCourse(){
        return new ServiceResponse<>(HttpStatus.OK, courseService.viewAllCourses());
    }

    @GetMapping("/search/path/{courseId}")
    public ServiceResponse<CourseResponseDTO> findCourse(@PathVariable Integer courseId){
        return new ServiceResponse<>(HttpStatus.OK, courseService.findByCourseId(courseId));
    }

    @GetMapping("/search/request")
    public ServiceResponse<CourseResponseDTO> findCourseUsingRequestParam(@RequestParam(required = false) Integer courseId){
        return new ServiceResponse<>(HttpStatus.OK, courseService.findByCourseId(courseId));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable int courseId){
        log.info("CourseController::deleteCourse deleting course with id: {}", courseId);
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{courseId}")
    public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable int courseId, @RequestBody CourseRequestDTO course){
        log.info("CourseController::updateCourse request payload: {} and {}", AppUtils.convertObjectToJson(course), courseId);
        CourseResponseDTO courseResponseDTO = courseService.updateCourse(courseId, course);
        ServiceResponse<CourseResponseDTO> response = new ServiceResponse<>(HttpStatus.OK, courseResponseDTO);
        log.info("CourseController::updateCourse response body: {}", AppUtils.convertObjectToJson(response));
        return response;
    }

    @GetMapping("/log")
    public String loggingLevel(){
        log.trace("trace message");
        log.debug("debug message");
        log.info("info message");
        log.warn("warn message");
        log.error("error message");
        return "Log Printed in Console :)";
    }

}

package com.attraya.controller;

import com.attraya.dto.CourseRequestDTO;
import com.attraya.dto.CourseResponseDTO;
import com.attraya.dto.ServiceResponse;
import com.attraya.service.CourseService;
import com.attraya.util.AppUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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

    @Operation(summary = "Add a new Course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course added successfully",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CourseResponseDTO.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
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

    @Operation(summary = "Fetch all courses")
    @GetMapping
    public ServiceResponse<List<CourseResponseDTO>> findAllCourse(){
        return new ServiceResponse<>(HttpStatus.OK, courseService.viewAllCourses());
    }

    @Operation(summary = "Find Course by Course ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course Found",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CourseResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Course not Found with given ID")
    })
    @GetMapping("/search/path/{courseId}")
    public ServiceResponse<CourseResponseDTO> findCourse(@PathVariable Integer courseId){
        return new ServiceResponse<>(HttpStatus.OK, courseService.findByCourseId(courseId));
    }

    @Operation(summary = "Find course by courseId using RequestParam")
    @GetMapping("/search/request")
    public ServiceResponse<CourseResponseDTO> findCourseUsingRequestParam(@RequestParam(required = false) Integer courseId){
        return new ServiceResponse<>(HttpStatus.OK, courseService.findByCourseId(courseId));
    }

    @Operation(summary = "Delete course by courseId")
    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable int courseId){
        log.info("CourseController::deleteCourse deleting course with id: {}", courseId);
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Update the course in system")
    @PutMapping("/{courseId}")
    public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable int courseId, @RequestBody CourseRequestDTO course){
        log.info("CourseController::updateCourse request payload: {} and {}", AppUtils.convertObjectToJson(course), courseId);
        CourseResponseDTO courseResponseDTO = courseService.updateCourse(courseId, course);
        ServiceResponse<CourseResponseDTO> response = new ServiceResponse<>(HttpStatus.OK, courseResponseDTO);
        log.info("CourseController::updateCourse response body: {}", AppUtils.convertObjectToJson(response));
        return response;
    }

    @Operation(summary = "For testing purpose only")
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

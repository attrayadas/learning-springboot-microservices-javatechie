package com.attraya.facultyservice.service;

import com.attraya.facultyservice.dto.CourseRequestDTO;
import com.attraya.facultyservice.dto.CourseResponseDTO;
import com.attraya.facultyservice.dto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Service
public class FacultyService {

    private static final String BASE_URL = "http://localhost:9090/";

    @Autowired
    private RestTemplate restTemplate;

    public ServiceResponse<CourseResponseDTO> addNewCourseToDashboard(CourseRequestDTO courseRequestDTO){
        return restTemplate.postForObject(BASE_URL+"api/course", courseRequestDTO, ServiceResponse.class);
    }

    public ServiceResponse<List<CourseResponseDTO>> fetchAllCourses(){
        return restTemplate.getForObject(BASE_URL + "api/course", ServiceResponse.class);
    }

    public ServiceResponse<CourseResponseDTO> findCourseById(Integer courseId){
        return restTemplate.getForObject(BASE_URL + "api/course/search/path/"+courseId, ServiceResponse.class);
    }

    public ServiceResponse<CourseResponseDTO> findCourseByIdUsingRequestParam(Integer courseId){
        HashMap<String, Integer> requestMap = new HashMap<>();
        requestMap.put("courseId", courseId);
        return restTemplate.getForObject(BASE_URL + "api/course/search/request", ServiceResponse.class, requestMap);
    }

    public void updateCourseInDashboard(int courseId, CourseRequestDTO courseRequestDTO){
        restTemplate.put(BASE_URL + "/api/course/"+courseId, courseRequestDTO);
    }

    public void deleteCourseFromDashboard(int courseId){
        restTemplate.delete(BASE_URL + "api/course/"+courseId);
    }
}

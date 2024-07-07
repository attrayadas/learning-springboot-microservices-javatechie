package com.attraya.util;

import com.attraya.dto.CourseRequestDTO;
import com.attraya.dto.CourseResponseDTO;
import com.attraya.entity.CourseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppUtils {

    // DTO -> ENTITY
    public static CourseEntity mapDtoToEntity(CourseRequestDTO courseRequestDTO){
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(courseRequestDTO.getName());
        courseEntity.setTrainerName(courseRequestDTO.getTrainerName());
        courseEntity.setDuration(courseRequestDTO.getDuration());
        courseEntity.setStartDate(courseRequestDTO.getStartDate());
        courseEntity.setCourseType(courseRequestDTO.getCourseType());
        courseEntity.setFees(courseRequestDTO.getFees());
        courseEntity.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
        courseEntity.setDescription(courseRequestDTO.getDescription());
        courseEntity.setEmailId(courseRequestDTO.getEmailId());
        courseEntity.setContact(courseRequestDTO.getContact());
        return courseEntity;
    }

    public static CourseResponseDTO mapEntityToDto(CourseEntity courseEntity){
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setCourseId(courseEntity.getCourseId());
        courseResponseDTO.setName(courseEntity.getName());
        courseResponseDTO.setTrainerName(courseEntity.getTrainerName());
        courseResponseDTO.setDuration(courseEntity.getDuration());
        courseResponseDTO.setStartDate(courseEntity.getStartDate());
        courseResponseDTO.setCourseType(courseEntity.getCourseType());
        courseResponseDTO.setFees(courseEntity.getFees());
        courseResponseDTO.setCertificateAvailable(courseEntity.isCertificateAvailable());
        courseResponseDTO.setDescription(courseEntity.getDescription());
        courseResponseDTO.setEmailId(courseEntity.getEmailId());
        courseResponseDTO.setContact(courseEntity.getContact());
        return courseResponseDTO;
    }

    public static String convertObjectToJson(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.attraya.service;

import com.attraya.dao.CourseDao;
import com.attraya.dto.CourseRequestDTO;
import com.attraya.dto.CourseResponseDTO;
import com.attraya.entity.CourseEntity;
import com.attraya.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CourseService {

    private CourseDao courseDao;

    // create a course object in DB
    public CourseResponseDTO onBoardNewCourse(CourseRequestDTO courseRequestDTO){
        // convert dto to entity
        CourseEntity courseEntity = AppUtils.mapDtoToEntity(courseRequestDTO);
        CourseEntity entity = courseDao.save(courseEntity);
        // convert entity to dto
        CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDto(entity);
        courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
        return courseResponseDTO;
    }

    // load all the course from Database
    public List<CourseResponseDTO> viewAllCourses(){
        Iterable<CourseEntity> courseEntities = courseDao.findAll();
        return StreamSupport.stream(courseEntities.spliterator(), false)
                .map(AppUtils::mapEntityToDto)
                .collect(Collectors.toList());
    }

    // filter course by course id
    public CourseResponseDTO findByCourseId(Integer courseId){
        CourseEntity courseEntity = courseDao.findById(courseId)
                .orElseThrow(() -> new RuntimeException(courseId + " not valid!"));
        return AppUtils.mapEntityToDto(courseEntity);
    }

    // delete course
    public void deleteCourse(int courseId){
        courseDao.deleteById(courseId);
    }

    // update the course
    public CourseResponseDTO updateCourse(int courseId, CourseRequestDTO courseRequestDTO){
        // get the existing object
        CourseEntity existingCourseEntity = courseDao.findById(courseId).orElse(null);
        // modify existing object with new value
        existingCourseEntity.setName(courseRequestDTO.getName());
        existingCourseEntity.setTrainerName(courseRequestDTO.getTrainerName());
        existingCourseEntity.setDuration(courseRequestDTO.getDuration());
        existingCourseEntity.setStartDate(courseRequestDTO.getStartDate());
        existingCourseEntity.setCourseType(courseRequestDTO.getCourseType());
        existingCourseEntity.setFees(courseRequestDTO.getFees());
        existingCourseEntity.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
        existingCourseEntity.setDescription(courseRequestDTO.getDescription());
        // save modified value
        CourseEntity updatedCourseEntity = courseDao.save(existingCourseEntity);
        return AppUtils.mapEntityToDto(updatedCourseEntity);
    }
}

package com.attraya.service;

import com.attraya.dao.CourseDao;
import com.attraya.dto.CourseRequestDTO;
import com.attraya.dto.CourseResponseDTO;
import com.attraya.entity.CourseEntity;
import com.attraya.exception.CourseServiceBusinessException;
import com.attraya.util.AppUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    Logger log = LoggerFactory.getLogger(CourseService.class);

    // create a course object in DB
    public CourseResponseDTO onBoardNewCourse(CourseRequestDTO courseRequestDTO) {
        // convert dto to entity
        CourseEntity courseEntity = AppUtils.mapDtoToEntity(courseRequestDTO);
        CourseEntity entity = null;
        log.info("CourseService::onBoardNewCourse method execution started");
        try {
            entity = courseDao.save(courseEntity);
            log.debug("course entity response from Database {}", AppUtils.convertObjectToJson(entity));
            // convert entity to dto
            CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDto(entity);
            courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
            log.debug("CourseService::onBoardNewCourse method response {}", AppUtils.convertObjectToJson(courseResponseDTO));
            log.info("CourseService::onBoardNewCourse method execution ended");
            return courseResponseDTO;
        } catch (Exception e) {
            log.error("CourseService::onBoardNewCourse exception occurred while persisting the course object to DB");
            throw new CourseServiceBusinessException("onboardNewCourse service method failed!");
        }
    }

    // load all the course from Database
    public List<CourseResponseDTO> viewAllCourses() {
        Iterable<CourseEntity> courseEntities = null;
        try {
            courseEntities = courseDao.findAll();
            return StreamSupport.stream(courseEntities.spliterator(), false)
                    .map(AppUtils::mapEntityToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CourseServiceBusinessException("viewAllCourses service method failed!");
        }
    }

    // filter course by course id
    public CourseResponseDTO findByCourseId(Integer courseId) {
        CourseEntity courseEntity = courseDao.findById(courseId)
                .orElseThrow(() -> new CourseServiceBusinessException(courseId + " doesn't exist in system :("));
        return AppUtils.mapEntityToDto(courseEntity);
    }

    // delete course
    public void deleteCourse(int courseId) {
        log.info("CourseService::deleteCourse method execution started");
        try {
            log.debug("CourseService::deleteCourse method input {}", courseId);
            courseDao.deleteById(courseId);
        } catch (Exception e) {
            log.error("CourseService::deleteCourse exception occurred while deleting the course object {}", e.getMessage());
            throw new CourseServiceBusinessException("deleteCourse service method failed...");
        }
        log.info("CourseService::deleteCourse method execution ended");
    }

    // update the course
    public CourseResponseDTO updateCourse(int courseId, CourseRequestDTO courseRequestDTO) {
        log.info("CourseService::updateCourse method execution started");
        try {
            log.debug("CourseService::updateCourse request payload {} & id {}", AppUtils.convertObjectToJson(courseRequestDTO), courseId);
            // get the existing object
            CourseEntity existingCourseEntity = courseDao.findById(courseId).orElseThrow(()->new RuntimeException("course object not present in DB with courseId "+courseId));
            log.debug("CourseService::updateCourse getting existing course as {}", AppUtils.convertObjectToJson(existingCourseEntity));
            // modify existing object with new value
            existingCourseEntity.setName(courseRequestDTO.getName());
            existingCourseEntity.setTrainerName(courseRequestDTO.getTrainerName());
            existingCourseEntity.setDuration(courseRequestDTO.getDuration());
            existingCourseEntity.setStartDate(courseRequestDTO.getStartDate());
            existingCourseEntity.setCourseType(courseRequestDTO.getCourseType());
            existingCourseEntity.setFees(courseRequestDTO.getFees());
            existingCourseEntity.setEmailId(courseRequestDTO.getEmailId());
            existingCourseEntity.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
            existingCourseEntity.setDescription(courseRequestDTO.getDescription());
            // save modified value
            CourseEntity updatedCourseEntity = courseDao.save(existingCourseEntity);
            CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDto(updatedCourseEntity);
            log.debug("CourseService::updateCourse getting updated course as {}", AppUtils.convertObjectToJson(courseResponseDTO));
            log.info("CourseService::updateCourse method execution ended");
            return courseResponseDTO;
        } catch (Exception e) {
            log.error("CourseService::updateCourse exception occurred while updating the course object {}", e.getMessage());
            throw new CourseServiceBusinessException("updateCourse service method failed");
        }
    }
}

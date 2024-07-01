package com.attraya.dao;

import com.attraya.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

public interface CourseDao extends CrudRepository<CourseEntity, Integer> {
}

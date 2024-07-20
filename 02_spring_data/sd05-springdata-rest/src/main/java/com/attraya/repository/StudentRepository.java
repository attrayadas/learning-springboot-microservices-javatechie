package com.attraya.repository;

import com.attraya.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "student-api", path = "student-api")
public interface StudentRepository extends JpaRepository<Student, Integer> {
    //-------------------------------------------------
    // Everything is available at http://localhost:9090
    //-------------------------------------------------

    // To access this endpoint: http://localhost:9090/student-api/search/findBySection?section=XII
    List<Student> findBySection(String section);

    // To access pagination & sorting: http://localhost:9090/student-api?page=0&size=2&sort=name

}

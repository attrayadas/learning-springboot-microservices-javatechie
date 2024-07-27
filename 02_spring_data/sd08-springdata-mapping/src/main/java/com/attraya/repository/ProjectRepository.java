package com.attraya.repository;

import com.attraya.bo.ProjectEngineer;
import com.attraya.bo.ProjectEngineerResponseBO;
import com.attraya.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    // Not recommended as no object binding happening
    @Query(value = "SELECT p.name, p.project_code, e.name, e.email FROM project p join engineer e " +
            "on p.project_id = e.project_engineer_fk", nativeQuery = true)
    String[] getProjectSpecificInfoWithSQL();

    // Not recommended as no object binding happening
    @Query(value = "select p.name, p.projectCode, e.name, e.email from Project p JOIN p.engineers e")
    String[] getProjectSpecificInfoWithJPQL();

    @Query(value = "select new com.attraya.bo.ProjectEngineerResponseBO(p.name, p.projectCode, e.name, e.email) from Project p JOIN p.engineers e")
    List<ProjectEngineerResponseBO> getProjectSpecificInfoWithObjectBinding();

    @Query(value = "select new com.attraya.bo.ProjectEngineerResponseBO(p.name, e.name) from Project p JOIN p.engineers e")
    List<ProjectEngineerResponseBO> getProjectSpecificInfoWithObjectBindingV2();

    @Query(value = "select p.name, p.project_code, e.name as engineer_name, e.email as engineer_email from project p join engineer e on p.project_id = e.project_engineer_fk", nativeQuery = true)
    List<ProjectEngineer> getProjectSpecificInfoWithObjectBindingV3();
}

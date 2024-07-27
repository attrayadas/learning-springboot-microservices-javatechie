package com.attraya.controller;

import com.attraya.bo.ProjectEngineer;
import com.attraya.bo.ProjectEngineerResponseBO;
import com.attraya.entity.Engineer;
import com.attraya.entity.Project;
import com.attraya.service.ProjectManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectManagementController {

    private final ProjectManagementService projectManagementService;

    @PostMapping("/projects")
    public Project saveProject(@RequestBody Project project){
        return projectManagementService.saveProject(project);
    }

    @GetMapping("/projects")
    public List<Project> getProjects(){
        return projectManagementService.getProjects();
    }

    @GetMapping("/engineers")
    public List<Engineer> getEngineers(){
        return projectManagementService.getEngineers();
    }

    @DeleteMapping("/project/{projectId}")
    public String deleteProject(@PathVariable int projectId){
        return projectManagementService.deleteProject(projectId);
    }

    @GetMapping("/join/sql")
    public String[] getProjectSpecificInfoSQL(){
        return projectManagementService.getProjectSpecificInfoSQL();
    }

    @GetMapping("/join/jpql")
    public String[] getProjectSpecificInfoJPQL(){
        return projectManagementService.getProjectSpecificInfoJPQL();
    }

    @GetMapping("/join/object")
    public List<ProjectEngineerResponseBO> getProjectSpecificInfoWithObjectBinding(){
        return projectManagementService.getProjectSpecificInfoWithObjectBinding();
    }

    @GetMapping("/join/object/v2")
    public List<ProjectEngineerResponseBO> getProjectSpecificInfoWithObjectBindingV2(){
        return projectManagementService.getProjectSpecificInfoWithObjectBindingV2();
    }

    @GetMapping("/join/object/v3")
    public List<ProjectEngineer> getProjectSpecificInfoWithObjectBindingV3(){
        return projectManagementService.getProjectSpecificInfoWithObjectBindingV3();
    }
}

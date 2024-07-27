package com.attraya.controller;

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
}

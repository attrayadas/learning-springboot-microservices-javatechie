package com.attraya.service;

import com.attraya.bo.ProjectEngineer;
import com.attraya.bo.ProjectEngineerResponseBO;
import com.attraya.entity.Engineer;
import com.attraya.entity.Project;
import com.attraya.repository.EngineerRepository;
import com.attraya.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectManagementService {

    private final ProjectRepository projectRepository;
    private final EngineerRepository engineerRepository;

    public Project saveProject(Project project){
        return projectRepository.save(project);
    }

    public List<Project> getProjects(){
        return projectRepository.findAll();
    }

    public List<Engineer> getEngineers(){
        return engineerRepository.findAll();
    }

    public String deleteProject(int projectId){
        projectRepository.deleteById(projectId);
        return "project: "+projectId+" deleted!";
    }

    public String[] getProjectSpecificInfoSQL(){
        return projectRepository.getProjectSpecificInfoWithSQL();
    }

    public String[] getProjectSpecificInfoJPQL(){
        return projectRepository.getProjectSpecificInfoWithJPQL();
    }

    public List<ProjectEngineerResponseBO> getProjectSpecificInfoWithObjectBinding(){
        return projectRepository.getProjectSpecificInfoWithObjectBinding();
    }

    public List<ProjectEngineerResponseBO> getProjectSpecificInfoWithObjectBindingV2(){
        return projectRepository.getProjectSpecificInfoWithObjectBindingV2();
    }

    public List<ProjectEngineer> getProjectSpecificInfoWithObjectBindingV3(){
        return projectRepository.getProjectSpecificInfoWithObjectBindingV3();
    }
}

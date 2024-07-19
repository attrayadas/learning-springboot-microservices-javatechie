package com.attraya.service;

import com.attraya.model.Task;
import com.attraya.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    public Task saveTask(Task task){
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTask(String taskId){
        return taskRepository.findById(taskId).get();
    }

    public Task updateTask(Task taskRequest){
        // get existing object from DB by taskId
        // then set new value from request
        Task existingTask = taskRepository.findById(taskRequest.getTaskId()).get();
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setPriority(taskRequest.getPriority());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());
        return taskRepository.save(existingTask);
    }

    public String deleteTask(String taskId){
        taskRepository.deleteById(taskId);
        return taskId + " task is deleted!";
    }

    public List<Task> getTaskByAssigneeAndPriority(String assignee, String priority){
        return taskRepository.findByAssigneeAndPriority(assignee, priority);
    }

    public List<Task> findTaskByAssigneeAndPriority(String assignee, String priority){
        return taskRepository.findTaskWithAssigneeAndPriority(assignee, priority);
    }

    public List<Task> findTaskByAssigneeAndPriorityV2(String assignee, String priority){
        return taskRepository.findTaskWithAssigneeAndPriorityV2(assignee, priority);
    }
}

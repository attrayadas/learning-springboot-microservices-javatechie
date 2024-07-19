package com.attraya.controller;

import com.attraya.model.Task;
import com.attraya.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/task")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @PostMapping
    public Task addNewTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }

    @GetMapping
    public List<Task> findAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{taskId}")
    public Task findTaskByTaskId(@PathVariable String taskId){
        return taskService.getTask(taskId);
    }

    @PutMapping
    public Task updateTask(@RequestBody Task task){
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable String taskId){
        return taskService.deleteTask(taskId);
    }

    @GetMapping("/assignee/{assignee}/priority/{priority}")
    public List<Task> getTaskByAssigneeAndPriority(@PathVariable String assignee, @PathVariable String priority){
        return taskService.getTaskByAssigneeAndPriority(assignee, priority);
    }

    @GetMapping("v2/assignee/{assignee}/priority/{priority}")
    public List<Task> findTaskByAssigneeAndPriority(@PathVariable String assignee, @PathVariable String priority){
        return taskService.findTaskByAssigneeAndPriority(assignee, priority);
    }

    @GetMapping("v3/assignee/{assignee}/priority/{priority}")
    public List<Task> findTaskByAssigneeAndPriorityV3(@PathVariable String assignee, @PathVariable String priority){
        return taskService.findTaskByAssigneeAndPriorityV2(assignee, priority);
    }
}

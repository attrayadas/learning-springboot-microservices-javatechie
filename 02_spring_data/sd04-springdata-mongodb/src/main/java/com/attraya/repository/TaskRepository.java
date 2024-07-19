package com.attraya.repository;

import com.attraya.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByAssigneeAndPriority(String assignee, String priority);

    // select * from table where assignee=:0 and priority=:1
    @Query("{assignee: ?0, priority: ?1}")
    List<Task> findTaskWithAssigneeAndPriority(String assignee, String priority);

    @Query(value = "{assignee: ?0, priority: ?1}", fields = "{'description': 1 ,'storyPoint': 2}")
    List<Task> findTaskWithAssigneeAndPriorityV2(String assignee, String priority);
}

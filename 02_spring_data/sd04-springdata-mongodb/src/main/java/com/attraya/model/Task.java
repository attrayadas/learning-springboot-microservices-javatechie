package com.attraya.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// collection is same as table in RDBMS
@Data
@Document(collection = "Tasks") // @Entity @Table(name = "")
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    private String taskId;
    private String description;
    private String priority;
    private String assignee;
    private int storyPoint;
}

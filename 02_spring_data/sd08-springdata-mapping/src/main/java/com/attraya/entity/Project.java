package com.attraya.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;
    private String name;
    private String projectCode;
    @OneToMany(
            cascade = CascadeType.PERSIST,
            orphanRemoval = true // If parent is deleted, child should also be deleted (can be replaced with CascadeType.ALL)
    )
    @JoinColumn(name = "project_engineer_fk", referencedColumnName = "projectId")
    private List<Engineer> engineers;
}

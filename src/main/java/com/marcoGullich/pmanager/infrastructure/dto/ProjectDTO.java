package com.marcoGullich.pmanager.infrastructure.dto;

import com.marcoGullich.pmanager.domain.entity.Project;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDTO {

    private String id;
    private String name;
    private String description;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private String status;


    public ProjectDTO() {
    }

    public ProjectDTO(String id, String name, String description, LocalDate initialDate, LocalDate finalDate, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.status = status;
    }


    public static ProjectDTO create(Project project){
        return new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getInitialDate(),
                project.getFinalDate(),
                project.getStatus().toString()
        );
    }
}

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

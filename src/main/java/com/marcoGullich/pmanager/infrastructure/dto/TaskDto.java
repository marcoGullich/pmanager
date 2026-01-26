package com.marcoGullich.pmanager.infrastructure.dto;

import com.marcoGullich.pmanager.domain.entity.Task;
import com.marcoGullich.pmanager.domain.model.TaskStatus;

public class TaskDto {

    public TaskDto() {
    }

    public TaskDto(String id, String title, String description, Integer numberOfDays, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.numberOfDays = numberOfDays;
        this.status = status;
    }

    private String id;
    private String title;
    private String description;
    private Integer numberOfDays;
    private TaskStatus status;


    public TaskDto create(Task task){
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getNumberOfDays(),
                task.getStatus()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}

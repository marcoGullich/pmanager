package com.marcoGullich.pmanager.infrastructure.dto;

import com.marcoGullich.pmanager.domain.entity.Task;
import com.marcoGullich.pmanager.domain.model.TaskStatus;

import java.util.Optional;

public class TaskDto {

    public TaskDto() {
    }

    private String id;
    private String title;
    private String description;
    private Integer numberOfDays;
    private TaskStatus status;
    private ProjectDTO project;
    private MemberDto assignedMember;

    public TaskDto(String id, String title, String description, Integer numberOfDays, TaskStatus status, ProjectDTO projectDTO, MemberDto memberDto) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.numberOfDays = numberOfDays;
        this.status = status;
        this.assignedMember = memberDto;
        this.project = projectDTO;
    }


    public static TaskDto create(Task task){
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getNumberOfDays(),
                task.getStatus(),
                Optional.ofNullable(task.getProject()).map(ProjectDTO::create).orElse(null),
                Optional.ofNullable(task.getAssignedMember()).map(MemberDto::create).orElse(null)
        );
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }

    public MemberDto getAssignedMember() {
        return assignedMember;
    }

    public void setAssignedMember(MemberDto assignedMember) {
        this.assignedMember = assignedMember;
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

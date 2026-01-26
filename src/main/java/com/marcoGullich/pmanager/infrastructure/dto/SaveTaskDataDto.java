package com.marcoGullich.pmanager.infrastructure.dto;

import com.marcoGullich.pmanager.domain.model.TaskStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class SaveTaskDataDto {

    @NotNull(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Description cannot be empty")
    @Size(min = 1, max = 150, message = "Invalid description")
    private String description;

    @NotNull(message = "NumberOfDays cannot be empty")
    @Positive(message = "Number of days must be positive")
    private Integer numberOfDays;
    
    private TaskStatus status;

}

package com.marcoGullich.pmanager.infrastructure.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SaveProjectDataDTO {

    private String name;
    private String description;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private String status;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public String getStatus() {
        return status;
    }
}

package com.marcoGullich.pmanager.infrastructure.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SaveProjectDataDTO {

    @NotNull(message = "Name cannot be empty")
    @Size(min = 1, max = 80, message = "Invalid name")
    private String name;

    @NotNull(message = "Description cannot be empty")
    @Size(min = 1, max = 150, message = "Invalid description")
    private String description;

    @NotNull(message = "InitialDate cannot be empty")
    private LocalDate initialDate;

    @NotNull(message = "FinalDate cannot be empty")
    private LocalDate finalDate;

    private String status;

    @AssertTrue(message = "Dates are not consistent")
    @SuppressWarnings("unused")
    private boolean isInitialDateBeforeFinalDate() {
        return initialDate.isBefore(finalDate);
    }

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

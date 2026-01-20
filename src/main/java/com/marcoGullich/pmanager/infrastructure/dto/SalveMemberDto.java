package com.marcoGullich.pmanager.infrastructure.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SalveMemberDto {

    @NotNull(message = "Name cannot be empty")
    @Size(min = 1, max = 80, message = "Invalid member name")
    private String name;

    @NotNull(message = "E-mail cannot be empty")
    @Email(message = "E-mail is not valid")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

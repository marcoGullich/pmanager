package com.marcoGullich.pmanager.domain.document;

import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "api_key")
public class ApiKey {

    @Id
    private String id;

    private String value;

    private Instant expiresWhen;

    @CreatedDate
    private Instant createdWhen;

    public ApiKey(Instant createdWhen, Instant expiresWhen, String value, String id) {
        this.createdWhen = createdWhen;
        this.expiresWhen = expiresWhen;
        this.value = value;
        this.id = id;
    }

    public ApiKey() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Instant getExpiresWhen() {
        return expiresWhen;
    }

    public void setExpiresWhen(Instant expiresWhen) {
        this.expiresWhen = expiresWhen;
    }

    public Instant getCreatedWhen() {
        return createdWhen;
    }

    public void setCreatedWhen(Instant createdWhen) {
        this.createdWhen = createdWhen;
    }
}

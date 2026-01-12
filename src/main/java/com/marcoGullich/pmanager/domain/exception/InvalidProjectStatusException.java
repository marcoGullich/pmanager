package com.marcoGullich.pmanager.domain.exception;

import com.marcoGullich.pmanager.infrastructure.exception.RequestException;

public class InvalidProjectStatusException extends RequestException {

    public InvalidProjectStatusException(String statusStr) {
        super("InvalidProjectStatus", "Invalid project status: " + statusStr);
    }
}

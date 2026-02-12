package com.marcoGullich.pmanager.domain.exception;

import com.marcoGullich.pmanager.infrastructure.exception.RequestException;

public class TaksNotFoundException extends RequestException {


    public TaksNotFoundException(String taskId) {
        super("TaskNotFound", "Task not found: " + taskId);
    }
}

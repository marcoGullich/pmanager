package com.marcoGullich.pmanager.domain.exception;

import com.marcoGullich.pmanager.infrastructure.exception.RequestException;

public class ProjectNotFoundException extends RequestException {



    public ProjectNotFoundException(String projectId) {
        super("ProjectNotFound", "Project not found: " + projectId);
    }
}

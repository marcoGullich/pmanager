package com.marcoGullich.pmanager.domain.exception;

import com.marcoGullich.pmanager.infrastructure.exception.RequestException;

public class DuplicateProjectException extends RequestException {



    public DuplicateProjectException(String name) {
        super("ProjectDuplicate", "Project with the name already exists: " + name);
    }
}

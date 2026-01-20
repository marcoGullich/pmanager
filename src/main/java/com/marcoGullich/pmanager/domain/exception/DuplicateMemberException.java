package com.marcoGullich.pmanager.domain.exception;

import com.marcoGullich.pmanager.infrastructure.exception.RequestException;

public class DuplicateMemberException extends RequestException {



    public DuplicateMemberException(String email) {
        super("MemberDuplicate", "Member with the e-mail already exists: " + email);
    }
}

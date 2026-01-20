package com.marcoGullich.pmanager.domain.exception;

import com.marcoGullich.pmanager.infrastructure.exception.RequestException;

public class MemberNotFoundException extends RequestException {


    public MemberNotFoundException(String memberId) {
        super("MemberNotFound", "Member not found: " + memberId);
    }
}

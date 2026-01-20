package com.marcoGullich.pmanager.infrastructure.dto;

import com.marcoGullich.pmanager.domain.entity.Member;

public class MemberDto {

    private String id;
    private String secret;
    private String name;
    private String email;

    public MemberDto(String id, String secret, String name, String email) {
        this.id = id;
        this.secret = secret;
        this.name = name;
        this.email = email;
    }

    public static MemberDto create(Member member){
        return new MemberDto(
                member.getId(),
                member.getSecret(),
                member.getName(),
                member.getEmail()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

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

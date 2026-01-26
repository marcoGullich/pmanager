package com.marcoGullich.pmanager.infrastructure.dto;

import com.marcoGullich.pmanager.domain.entity.Member;
import com.marcoGullich.pmanager.domain.entity.Project;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class MemberDto {

    private String id;
    private String secret;
    private String name;
    private String email;
    private final Set<String> projectIds;

    public MemberDto(String id, String secret, String name, String email, Set<String> collect) {
        this.id = id;
        this.secret = secret;
        this.name = name;
        this.email = email;
        this.projectIds = collect;
    }


    public static MemberDto create(Member member){
        return new MemberDto(
                member.getId(),
                member.getSecret(),
                member.getName(),
                member.getEmail(),
                Optional
                        .ofNullable(member.getProjects())
                        .orElse(List.of())
                        .stream()
                        .map(Project::getId)
                        .collect(toSet())
        );
    }

    public Set<String> getProjectIds() {
        return projectIds;
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

package com.marcoGullich.pmanager.infrastructure.controller;

import com.marcoGullich.pmanager.domain.applicationservice.ProjectService;
import com.marcoGullich.pmanager.domain.entity.Project;
import com.marcoGullich.pmanager.infrastructure.dto.ProjectDTO;
import com.marcoGullich.pmanager.infrastructure.dto.SaveProjectDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.marcoGullich.pmanager.infrastructure.controller.RestConstants.PATH_PROJECTS;

@RestController
@RequestMapping(PATH_PROJECTS)
@RequiredArgsConstructor
public class ProjectRestResource {

    @Autowired
    private ProjectService service;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody SaveProjectDataDTO dto){
        Project project = service.createProject(dto);

        return ResponseEntity
                .created(URI.create(PATH_PROJECTS + "/" + project.getId()))
                .body(ProjectDTO.create(project));
    }
}

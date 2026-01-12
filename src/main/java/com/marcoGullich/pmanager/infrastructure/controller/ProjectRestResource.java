package com.marcoGullich.pmanager.infrastructure.controller;

import com.marcoGullich.pmanager.domain.applicationservice.ProjectService;
import com.marcoGullich.pmanager.domain.entity.Project;
import com.marcoGullich.pmanager.infrastructure.dto.ProjectDTO;
import com.marcoGullich.pmanager.infrastructure.dto.SaveProjectDataDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.marcoGullich.pmanager.infrastructure.controller.RestConstants.PATH_PROJECTS;

@RestController
@RequestMapping(PATH_PROJECTS)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ProjectRestResource {

    @Autowired
    private ProjectService service;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody @Valid SaveProjectDataDTO dto){
        Project project = service.createProject(dto);

        return ResponseEntity
                .created(URI.create(PATH_PROJECTS + "/" + project.getId()))
                .body(ProjectDTO.create(project));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> loadProject(@PathVariable("id") String projectId) {
        Project project = service.loadProject(projectId);
        return ResponseEntity.ok(ProjectDTO.create(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") String projectId) {
        service.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(
            @PathVariable("id") String projectId,
            @RequestBody @Valid SaveProjectDataDTO dto
    ) {
        Project project = service.updateProject(projectId, dto);
        return ResponseEntity.ok(ProjectDTO.create(project));
    }
}

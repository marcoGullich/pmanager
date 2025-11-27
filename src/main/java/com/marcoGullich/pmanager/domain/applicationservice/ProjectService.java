package com.marcoGullich.pmanager.domain.applicationservice;

import com.marcoGullich.pmanager.domain.entity.Project;
import com.marcoGullich.pmanager.domain.model.ProjectStatus;
import com.marcoGullich.pmanager.infrastructure.dto.SaveProjectDataDTO;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    public Project createProject(SaveProjectDataDTO dto) {

        Project project = Project
                .builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .initialDate(dto.getInitialDate())
                .finalDate(dto.getFinalDate())
                .status(ProjectStatus.PENDING)
                .build();

        return project;
    }
}

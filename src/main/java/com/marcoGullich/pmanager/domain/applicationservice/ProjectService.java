package com.marcoGullich.pmanager.domain.applicationservice;

import com.marcoGullich.pmanager.domain.entity.Project;
import com.marcoGullich.pmanager.domain.model.ProjectStatus;
import com.marcoGullich.pmanager.domain.repository.ProjectRepository;
import com.marcoGullich.pmanager.infrastructure.dto.SaveProjectDataDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //Construtor que receberá tds os atributos com "final"
public class ProjectService {

    private final ProjectRepository repository;

    @Transactional
    public Project createProject(SaveProjectDataDTO dto) {

        Project project = Project
                .builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .initialDate(dto.getInitialDate())
                .finalDate(dto.getFinalDate())
                .status(ProjectStatus.PENDING)
                .build();

        repository.save(project);

        return project;
    }
}

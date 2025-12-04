package com.marcoGullich.pmanager.domain.applicationservice;

import com.marcoGullich.pmanager.domain.entity.Project;
import com.marcoGullich.pmanager.domain.model.ProjectStatus;
import com.marcoGullich.pmanager.domain.repository.ProjectRepository;
import com.marcoGullich.pmanager.infrastructure.dto.SaveProjectDataDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor //Construtor que receberá tds os atributos com "final"
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Transactional
    public Project createProject(SaveProjectDataDTO dto) {
        Project project = new Project();
        project.setDescription(dto.getDescription());
        project.setName(dto.getName());
        project.setInitialDate(dto.getInitialDate());
        project.setFinalDate(dto.getFinalDate());
        project.setStatus(ProjectStatus.PENDING);

        repository.save(project);

        return project;
    }
}

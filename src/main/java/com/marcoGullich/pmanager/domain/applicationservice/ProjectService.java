package com.marcoGullich.pmanager.domain.applicationservice;

import com.marcoGullich.pmanager.domain.entity.Project;
import com.marcoGullich.pmanager.domain.exception.DuplicateProjectException;
import com.marcoGullich.pmanager.domain.exception.InvalidProjectStatusException;
import com.marcoGullich.pmanager.domain.exception.ProjectNotFoundException;
import com.marcoGullich.pmanager.domain.model.ProjectStatus;
import com.marcoGullich.pmanager.domain.repository.ProjectRepository;
import com.marcoGullich.pmanager.infrastructure.dto.SaveProjectDataDTO;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
//@RequiredArgsConstructor //Construtor que receberá tds os atributos com "final"
public class ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectRepository repository;

    @Transactional
    public Project createProject(SaveProjectDataDTO dto) {

        if(existsProjectWithName(dto.getName(), null)){
            throw new DuplicateProjectException(dto.getName());
        }

        Project project = new Project();
        project.setDescription(dto.getDescription());
        project.setName(dto.getName());
        project.setInitialDate(dto.getInitialDate());
        project.setFinalDate(dto.getFinalDate());
        project.setStatus(ProjectStatus.PENDING);

        repository.save(project);

        LOGGER.info("Project created: {}", project);

        return project;
    }

    public Project loadProject(String projectId) {
        return repository.findById(projectId)
                .orElseThrow(
                        () -> new ProjectNotFoundException(projectId)
                );
    }

    @Transactional
    public void deleteProject(String projetcId) {
        Project project = loadProject(projetcId);
        repository.delete(project);
    }

    @Transactional
    public Project updateProject(String id, SaveProjectDataDTO dto) {

        if(existsProjectWithName(dto.getName(), id)){
            throw new DuplicateProjectException(dto.getName());
        }
        Project project = loadProject(id);

        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setInitialDate(dto.getInitialDate());
        project.setFinalDate(dto.getFinalDate());
        project.setStatus(convertToProjectStatus(dto.getStatus()));

        return project;
    }


    private ProjectStatus convertToProjectStatus(String statusStr) {
        try {
            return ProjectStatus.valueOf(statusStr);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidProjectStatusException(statusStr);
        }
    }

    private boolean existsProjectWithName(String name, String idToExclude) {
        return repository
                .findByName(name)
                .filter(p -> !Objects.equals(p.getId(), idToExclude)) //Filtro para tirar da lista o que for diferente ao parâmetro idToExclude
                .isPresent();
    }

}

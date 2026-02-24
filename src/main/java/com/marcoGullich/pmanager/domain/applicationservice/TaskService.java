package com.marcoGullich.pmanager.domain.applicationservice;

import com.marcoGullich.pmanager.domain.entity.Member;
import com.marcoGullich.pmanager.domain.entity.Project;
import com.marcoGullich.pmanager.domain.entity.Task;
import com.marcoGullich.pmanager.domain.exception.InvalidTaskStatusException;
import com.marcoGullich.pmanager.domain.exception.TaksNotFoundException;
import com.marcoGullich.pmanager.domain.model.TaskStatus;
import com.marcoGullich.pmanager.domain.repository.TaskRepository;
import com.marcoGullich.pmanager.infrastructure.config.AppConfigProperties;
import com.marcoGullich.pmanager.infrastructure.dto.SaveTaskDataDto;
import com.marcoGullich.pmanager.infrastructure.util.PaginationHelper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MemberService memberService;

    private  PaginationHelper paginationHelper = new PaginationHelper();

    @Autowired
    private AppConfigProperties props;

    @Transactional
    public Task createTask(SaveTaskDataDto saveTaskDataDto) {
        Project project = getProjectIfPossible(saveTaskDataDto);

        Member member = getMemberIfPossible(saveTaskDataDto);

        Task task = new Task();
        task.setDescription(saveTaskDataDto.getDescription());
        task.setTitle(saveTaskDataDto.getTitle());
        task.setNumberOfDays(saveTaskDataDto.getNumberOfDays());
        task.setStatus(TaskStatus.PENDING);
        task.setProject(project);
        task.setAssignedMember(member);

        repository.save(task);

        return task;
    }

    public Task loadTask(String taskId) {
        return repository
                .findById(taskId)
                .orElseThrow(() -> new TaksNotFoundException(taskId));
    }

    @Transactional
    public void deleteTask(String taskId) {
        repository.delete(loadTask(taskId));
    }

    @Transactional
    public Task updateTask(String taskId, SaveTaskDataDto saveTaskDataDto) {
        Project project = getProjectIfPossible(saveTaskDataDto);

        Member member = getMemberIfPossible(saveTaskDataDto);

        Task task = loadTask(taskId);
        task.setTitle(saveTaskDataDto.getTitle());
        task.setDescription(saveTaskDataDto.getDescription());
        task.setNumberOfDays(saveTaskDataDto.getNumberOfDays());
        task.setStatus(saveTaskDataDto.getStatus());
        task.setAssignedMember(member);
        task.setProject(project);

        return task;
    }

    public Page<Task> findTasks(
            String projectId,
            String memberId,
            String statusStr,
            String partialTitle,
            Integer page,
            String direction,
            List<String> properties
    ){
        return repository.find(
                projectId,
                memberId,
                Optional.ofNullable(statusStr).map(this::convertToTaskStatus).orElse(null) ,
                partialTitle,
                paginationHelper.createPageable(page, props.getPageSize(), direction, properties)
        );
    }

    private Project getProjectIfPossible(SaveTaskDataDto saveTaskDataDto) {
        Project project = new Project();
        project = null;
        if (Objects.nonNull(saveTaskDataDto.getProjectId())) {
            project = projectService.loadProject(saveTaskDataDto.getProjectId());
        }
        return project;
    }

    private Member getMemberIfPossible(SaveTaskDataDto saveTaskDataDto) {
        Member member = new Member();
        member = null;
        if (Objects.nonNull(saveTaskDataDto.getMemberId())) {
            member = memberService.loadMemberById(saveTaskDataDto.getMemberId());
        }
        return member;
    }

    private TaskStatus convertToTaskStatus(String statusStr) {
        try {
            return TaskStatus.valueOf(statusStr);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidTaskStatusException(statusStr);
        }
    }
}

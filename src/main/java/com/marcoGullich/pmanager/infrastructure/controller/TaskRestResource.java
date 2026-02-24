package com.marcoGullich.pmanager.infrastructure.controller;

import com.marcoGullich.pmanager.domain.applicationservice.TaskService;
import com.marcoGullich.pmanager.domain.entity.Task;
import com.marcoGullich.pmanager.infrastructure.dto.SaveTaskDataDto;
import com.marcoGullich.pmanager.infrastructure.dto.TaskDto;
import com.marcoGullich.pmanager.infrastructure.util.SortProperties;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.marcoGullich.pmanager.infrastructure.controller.RestConstants.PATH_TASKS;

@RestController
@RequestMapping(PATH_TASKS)
public class TaskRestResource {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody @Valid SaveTaskDataDto saveTaskDataDto) {
        Task task = taskService.createTask(saveTaskDataDto);

        return ResponseEntity
                .created(URI.create(PATH_TASKS + "/" + task.getId()))
                .body(TaskDto.create(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> loadTask(@PathVariable("id") String taskId) {
        Task task = taskService.loadTask(taskId);
        return ResponseEntity.ok(TaskDto.create(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") String taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable("id") String taskId,
            @RequestBody @Valid SaveTaskDataDto saveTaskDataDto
    ) {
        Task task = taskService.updateTask(taskId, saveTaskDataDto);
        return ResponseEntity.ok(TaskDto.create(task));
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> findTasks(
            @RequestParam(value = "projectId", required = false) String projectId,
            @RequestParam(value = "memberId", required = false) String memberId,
            @RequestParam(value = "statusStr", required = false) String statusStr,
            @RequestParam(value = "partialTitle", required = false) String partialTitle,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "direction", required = false) String direction,
            @RequestParam(value = "sort", required = false) SortProperties properties
    ) {
        Page<Task> tasks = taskService.findTasks(
                projectId,
                memberId,
                statusStr,
                partialTitle,
                page,
                direction,
                Optional.ofNullable(properties).map(SortProperties::getSortPropertiesList).orElse(List.of()));
        return ResponseEntity.ok(tasks.stream().map(TaskDto::create).toList());
    }
}

package com.marcoGullich.pmanager.infrastructure.controller;

import com.marcoGullich.pmanager.domain.applicationservice.TaskService;
import com.marcoGullich.pmanager.domain.entity.Task;
import com.marcoGullich.pmanager.infrastructure.dto.SaveTaskDataDto;
import com.marcoGullich.pmanager.infrastructure.dto.TaskDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    public ResponseEntity<List<TaskDto>> findTasks(
            @RequestParam(value = "projectId", required = false) String projectId,
            @RequestParam(value = "memberId", required = false) String memberId,
            @RequestParam(value = "statusStr", required = false) String statusStr,
            @RequestParam(value = "partialTitle", required = false) String partialTitle
    ) {
        List<Task> tasks = taskService.findTasks(projectId, memberId, statusStr, partialTitle);
        return ResponseEntity.ok(tasks.stream().map(TaskDto::create).toList());
    }
}

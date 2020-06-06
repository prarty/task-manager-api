package com.hackerearth.taskmanager.api;

import com.hackerearth.taskmanager.api.request.CreateTaskRequest;
import com.hackerearth.taskmanager.api.response.GenericResponse;
import com.hackerearth.taskmanager.model.Task;
import com.hackerearth.taskmanager.service.TaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
public class TasksController {

    final TaskService taskService;

    @Autowired
    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{userId}/tasks")
    List<Task> getAllTasks(@PathVariable("userId") int userId) throws ValidationException {
        log.info("Started fetching the tasks for user {}", userId);
        List<Task> allTasks = taskService.getAllTasks(userId);
        log.info("tasks returned with size {}", allTasks.size());
        return allTasks;
    }

    @PostMapping("/{userId}/tasks/create")
    @ResponseStatus(HttpStatus.CREATED)
    GenericResponse<String> createTask(@PathVariable("userId") int userId, @Valid @RequestBody CreateTaskRequest request) throws ValidationException {
        log.info("Started creating task");
        taskService.createTask(userId, request);
         return new GenericResponse<>("Success");
    }
}

package com.hackerearth.taskmanager.api;

import com.hackerearth.taskmanager.api.request.CreateTaskRequest;
import com.hackerearth.taskmanager.api.response.GenericResponse;
import com.hackerearth.taskmanager.model.Task;
import com.hackerearth.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class TasksController {

    final TaskService taskService;

    @Autowired
    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{userId}/tasks")
    List<Task> getAllTasks(@PathVariable("userId") int userId) throws ValidationException {
        return taskService.getAllTasks(userId);
    }

    @PostMapping("/{userId}/tasks/create")
    @ResponseStatus(HttpStatus.CREATED)
    GenericResponse<String> createTask(@PathVariable("userId") int userId, @Valid @RequestBody CreateTaskRequest request) throws ValidationException {
         taskService.createTask(userId, request);
         return new GenericResponse<>("Success");
    }
}

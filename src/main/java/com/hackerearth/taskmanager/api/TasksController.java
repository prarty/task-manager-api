package com.hackerearth.taskmanager.api;

import com.hackerearth.taskmanager.model.Task;
import com.hackerearth.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

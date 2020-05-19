package com.hackerearth.taskmanager.api;

import com.hackerearth.taskmanager.api.response.TaskResponse;
import com.hackerearth.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class TasksController {

    final TaskService taskService;

    @Autowired
    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{userId}/tasks")
    TaskResponse getAllTasks(@PathVariable("userId") int userId){
        taskService.getAllTasks(userId);
        return null;
    }
}

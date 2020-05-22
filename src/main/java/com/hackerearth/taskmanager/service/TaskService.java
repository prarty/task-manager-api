package com.hackerearth.taskmanager.service;

import com.hackerearth.taskmanager.api.request.CreateTaskRequest;
import com.hackerearth.taskmanager.model.Task;
import com.hackerearth.taskmanager.model.User;
import com.hackerearth.taskmanager.repository.TaskRepository;
import com.hackerearth.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.ValidationException;
import java.util.Collections;
import java.util.List;

@Service
public class TaskService {

    final UserRepository userRepository;

    final TaskRepository taskRepository;

    @Autowired
    public TaskService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(int userId) throws ValidationException {
       User user = userRepository.findById(userId).orElseThrow(() -> new ValidationException("User not found"));

       return taskRepository.findAllByUserId(user.getId()).orElse(Collections.emptyList());
    }

    @Transactional
    public void createTask(int userId, CreateTaskRequest request) throws ValidationException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ValidationException("User not found"));
        Task newTask = Task.builder()
                            .description(request.getDescription())
                            .dueDate(request.getDueDate())
                            .label(request.getLabel())
                            .status(request.getStatus())
                            .user(user)
                            .build();

        taskRepository.save(newTask);
    }
}

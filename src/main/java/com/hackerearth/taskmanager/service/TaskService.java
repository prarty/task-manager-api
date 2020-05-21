package com.hackerearth.taskmanager.service;

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
import java.util.Optional;

@Service
public class TaskService {

    final UserRepository userRepository;

    final TaskRepository taskRepository;

    @Autowired
    public TaskService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Transactional
    public List<Task> getAllTasks(int userId) throws ValidationException {
       List<Task> response = null;
        System.out.println("id"+userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new ValidationException("not found"));
        System.out.println("user"+user);
        response = taskRepository.findAllByUserId(user.getId()).orElse(Collections.emptyList());
        System.out.println("response"+response);
        return response;
    }
}

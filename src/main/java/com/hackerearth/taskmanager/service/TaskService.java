package com.hackerearth.taskmanager.service;

import com.hackerearth.taskmanager.model.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {
    @Transactional
    public List<Task> getAllTasks(int userId) {
        return null;
    }
}

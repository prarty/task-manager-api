package com.hackerearth.taskmanager.repository;

import com.hackerearth.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
     Optional<List<Task>> findAllByUserId(int id);
}

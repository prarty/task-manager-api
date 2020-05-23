package com.hackerearth.taskmanager.repository;

import com.hackerearth.taskmanager.AbstractContainerBaseTest;
import com.hackerearth.taskmanager.model.Status;
import com.hackerearth.taskmanager.model.Task;
import com.hackerearth.taskmanager.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class TaskRepositoryTest extends AbstractContainerBaseTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void shouldFindAllTaskForUserWithId1(){
        User savedUser = userRepository.save(new User(1, "User1"));
        Task newTask = Task.builder().description("test task")
                .dueDate(LocalDate.now())
                .status(Status.PENDING)
                .user(savedUser)
                .build();
        taskRepository.save(newTask);

        Optional<List<Task>> userTasks = taskRepository.findAllByUserId(savedUser.getId());

        Assertions.assertEquals(1, userTasks.get().size());
        Assertions.assertEquals(newTask, userTasks.get().get(0));
    }
}

package com.hackerearth.taskmanager.service;

import com.hackerearth.taskmanager.api.request.CreateTaskRequest;
import com.hackerearth.taskmanager.model.Status;
import com.hackerearth.taskmanager.model.Task;
import com.hackerearth.taskmanager.model.User;
import com.hackerearth.taskmanager.repository.TaskRepository;
import com.hackerearth.taskmanager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.List;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    TaskRepository taskRepository;

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService(userRepository, taskRepository);
    }

    @Test
    void getAllTasksForUserWithUserId1() throws ValidationException {
        User user = new User(1, "Test1");
        Task newTask = Task.builder().description("test task")
                .dueDate(LocalDate.now())
                .status(Status.PENDING)
                .user(user)
                .build();

        when(userRepository.findById(1)).thenReturn(of(user));
        when(taskRepository.findAllByUserId(1)).thenReturn(of(List.of(newTask)));

        List<Task> allTasks = taskService.getAllTasks(1);

        assertEquals(1, allTasks.size());
        assertEquals(newTask, allTasks.get(0));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        assertThrows(ValidationException.class, () -> taskService.getAllTasks(1), "User not found");
    }

    @Test
    void createTask() throws ValidationException {
        CreateTaskRequest request = CreateTaskRequest.builder()
                                    .description("test1")
                                    .dueDate(LocalDate.now())
                                    .status(Status.PENDING)
                                    .build();
        User user = new User(1, "Test1");

        when(userRepository.findById(1)).thenReturn(of(user));

        taskService.createTask(1, request);

        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository, times(1)).save(captor.capture());

        Task savedTask  = captor.getValue();
        assertEquals(request.getDescription(), savedTask.getDescription());
        assertEquals(request.getDueDate(), savedTask.getDueDate());
        assertEquals(request.getStatus(), savedTask.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundWhileAddingNewTask() {
        assertThrows(ValidationException.class, () ->
                taskService.createTask(1, mock(CreateTaskRequest.class)),
                "User not found");
    }
}
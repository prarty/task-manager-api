package com.hackerearth.taskmanager.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hackerearth.taskmanager.AbstractContainerBaseTest;
import com.hackerearth.taskmanager.api.request.CreateTaskRequest;
import com.hackerearth.taskmanager.model.Status;
import com.hackerearth.taskmanager.model.Task;
import com.hackerearth.taskmanager.model.User;
import com.hackerearth.taskmanager.repository.TaskRepository;
import com.hackerearth.taskmanager.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TasksControllerTest extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        User savedUser = userRepository.save(new User(1, "temp"));

        Task newTask = Task.builder().description("test task")
                .dueDate(LocalDate.now())
                .status(Status.PENDING)
                .user(savedUser)
                .build();
        taskRepository.save(newTask);
    }

//    @AfterEach
//    void tearDown() {
//        taskRepository.deleteAll();
//        userRepository.deleteAll();
//    }

    @Test
    void getAllTasks() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/v1/users/1/tasks"))
                .andDo(print())
                .andExpect(status().isOk());

        String expectedResponse = "[" +
                "{" +
                "\"id\":1," +
                "\"description\":\"test task\"," +
                "\"dueDate\":\"2020-05-24\"," +
                "\"label\":null," +
                "\"status\":\"PENDING\"," +
                "\"user\":{\"id\":1,\"name\":\"temp\"}}" +
                "]\n";

        resultActions.andExpect(content().json(expectedResponse));
    }

    @Test
    void createTask() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        CreateTaskRequest request = CreateTaskRequest.builder()
                .description("test1")
                .dueDate(LocalDate.now())
                .status(Status.PENDING)
                .label("temp")
                .build();

        mockMvc.perform(
                post("/api/v1/users/1/tasks/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data").value("Success"));
    }
}
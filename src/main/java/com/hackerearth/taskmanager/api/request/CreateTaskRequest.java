package com.hackerearth.taskmanager.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hackerearth.taskmanager.model.Status;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Builder
public class CreateTaskRequest {

    @NotBlank
    String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dueDate;  //default h2 format yyyy-mm-dd

    String label;

    Status status;
}

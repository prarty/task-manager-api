package com.hackerearth.taskmanager.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "description")
    String description;

    @Column(name = "due_date")
    LocalDate dueDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;
}

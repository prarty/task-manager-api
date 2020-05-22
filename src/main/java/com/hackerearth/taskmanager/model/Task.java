package com.hackerearth.taskmanager.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "description")
    String description;

    @Column(name = "due_date")
    LocalDate dueDate;  //default h2 format yyyy-mm-dd

    @Column(name = "label")
    String label;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    User user;
}

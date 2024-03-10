package it.unimol.todo.models.entities;

import it.unimol.todo.models.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private Long id;

    private Long tagId;
    private Long doerId;

    private String description;
    private String title;

    private Priority priority;
    private Boolean completed;
    private Boolean starred;

    private Instant createdOn;
    private Instant updatedOn;
    private Instant completedOn;
    private Instant dueDate;
}
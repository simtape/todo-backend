package it.unimol.todo.models.entities;

import it.unimol.todo.models.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
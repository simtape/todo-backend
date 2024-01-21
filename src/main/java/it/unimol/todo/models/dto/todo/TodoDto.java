package it.unimol.todo.models.dto.todo;

import it.unimol.todo.models.entities.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private Instant createdOn;
    private Instant updatedOn;
    private Instant completedOn;
    private String tagName;

    public TodoDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getDescription();
        this.description = todo.getDescription();
        this.completed = todo.getCompleted();
        this.createdOn = todo.getCreatedOn();
        this.updatedOn = todo.getUpdatedOn();
        this.completedOn = todo.getCompletedOn();
        this.tagName = todo.getTag().getName();
    }
}

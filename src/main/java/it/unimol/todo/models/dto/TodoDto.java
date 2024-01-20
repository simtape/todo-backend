package it.unimol.todo.models.dto;

import it.unimol.todo.models.entities.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private Timestamp completedOn;

    public TodoDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getDescription();
        this.description = todo.getDescription();
        this.completed = todo.getCompleted();
        this.createdOn = todo.getCreatedOn();
        this.updatedOn = todo.getUpdatedOn();
        this.completedOn = todo.getCompletedOn();
    }
}

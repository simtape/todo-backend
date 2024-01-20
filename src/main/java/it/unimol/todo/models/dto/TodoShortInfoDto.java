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
public class TodoShortInfoDto {
    private Long id;
    private String title;
    private Timestamp createdOn;
    private Boolean completed;

    public TodoShortInfoDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.createdOn = todo.getCreatedOn();
        this.completed = todo.getCompleted();
    }

}

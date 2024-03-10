package it.unimol.todo.models.dto.todo;

import it.unimol.todo.models.entities.Todo;
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
public class TodoShortInfoDto {
    private Long id;
    private String title;
    private Instant createdOn;
    private Boolean completed;
    private String tagName;

    public TodoShortInfoDto(Todo todo, String tagName) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.createdOn = todo.getCreatedOn();
        this.completed = todo.getCompleted();
        this.tagName = tagName;
    }
}
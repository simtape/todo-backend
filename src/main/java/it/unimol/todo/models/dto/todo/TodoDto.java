package it.unimol.todo.models.dto.todo;

import it.unimol.todo.models.dto.doer.DoerShortInfoDto;
import it.unimol.todo.models.entities.Doer;
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
    private DoerShortInfoDto doer;

    public TodoDto(Todo todo, String tagName, Doer doer) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.description = todo.getDescription();
        this.completed = todo.getCompleted();
        this.createdOn = todo.getCreatedOn();
        this.updatedOn = todo.getUpdatedOn();
        this.completedOn = todo.getCompletedOn();
        this.tagName = tagName;
        if (doer != null) {
            this.doer = new DoerShortInfoDto(doer);
        }
    }
}

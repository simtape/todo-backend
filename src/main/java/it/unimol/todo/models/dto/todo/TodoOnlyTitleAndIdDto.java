package it.unimol.todo.models.dto.todo;

import it.unimol.todo.models.entities.Todo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoOnlyTitleAndIdDto {
    private Long id;
    private String title;

    public TodoOnlyTitleAndIdDto(Todo todo){
        this.id = todo.getId();
        this.title = todo.getTitle();
    }
}
package it.unimol.todo.models.dto.tag;

import it.unimol.todo.models.dto.todo.TodoOnlyTitleAndIdDto;
import it.unimol.todo.models.entities.Tag;
import it.unimol.todo.models.entities.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {
    private Long id;
    private String name;
    private List<TodoOnlyTitleAndIdDto> todos;

    public TagDto(Tag tag, List<Todo> todos) {
        this.id = tag.getId();
        this.name = tag.getName();

        if (todos != null) {
            this.todos = todos.stream()
                    .map(TodoOnlyTitleAndIdDto::new)
                    .toList();
        }
    }
}
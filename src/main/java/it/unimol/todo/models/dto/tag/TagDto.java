package it.unimol.todo.models.dto.tag;

import it.unimol.todo.models.dto.todo.TodoShortInfoDto;
import it.unimol.todo.models.entities.Tag;
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
    private List<TodoShortInfoDto> todos;

    public TagDto(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();

        if (tag.getTodos() != null) {
            this.todos = tag.getTodos()
                    .stream()
                    .map(TodoShortInfoDto::new)
                    .toList();
        }
    }
}
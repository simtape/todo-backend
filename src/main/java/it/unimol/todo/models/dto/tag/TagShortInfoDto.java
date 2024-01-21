package it.unimol.todo.models.dto.tag;

import it.unimol.todo.models.entities.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagShortInfoDto {
    private Long id;
    private String name;
    private int todosCount;

    public TagShortInfoDto(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();

        if (tag.getTodos() != null) {
            this.todosCount = tag.getTodos().size();
        }
    }
}

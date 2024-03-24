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

    public TagShortInfoDto(Tag tag, int todosCount) {
        this.id = tag.getId();
        this.name = tag.getName();
        this.todosCount = todosCount;
    }
}

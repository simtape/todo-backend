package it.unimol.todo.models.dto.doer;

import it.unimol.todo.models.dto.tag.TagDto;
import it.unimol.todo.models.dto.todo.TodoShortInfoDto;
import it.unimol.todo.models.entities.Doer;
import it.unimol.todo.models.entities.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DoerDto {
    private String name;
    private String surname;
    private String email;
    private List<TodoShortInfoDto> todoList;
    private Long tagId;

    public DoerDto(Doer doer){
        this.name = doer.getName();
        this.surname = doer.getSurname();
        this.email = doer.getEmail();
        this.tagId = doer.getTagId();
    }
}
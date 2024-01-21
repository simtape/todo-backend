package it.unimol.todo.models.dto.doer;

import it.unimol.todo.models.dto.tag.TagDto;
import it.unimol.todo.models.dto.todo.TodoShortInfoDto;

import java.util.List;

public class DoerDto {
    private String name;
    private String surname;
    private String email;
    private List<TodoShortInfoDto> todoList;
    private List<TagDto> tagList;
}

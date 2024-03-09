package it.unimol.todo.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doer {
    private Long id;
    private String name;
    private String surname;
    private String email;

    private List<Todo> todoList;
    private List<Tag> tagList;
}
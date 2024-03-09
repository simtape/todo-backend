package it.unimol.todo.models.request.todo;

import it.unimol.todo.models.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditTodoRequest {
    private String title;

    private String description;

    private Priority priority;

    private Long tagId;
}
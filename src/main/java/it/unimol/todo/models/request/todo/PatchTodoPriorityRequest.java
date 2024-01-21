package it.unimol.todo.models.request.todo;

import it.unimol.todo.models.Priority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchTodoPriorityRequest {
    private Priority priority;
}
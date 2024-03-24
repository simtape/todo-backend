package it.unimol.todo.models.request.todo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssignTodoRequest {
    private Long todoId;
    private Long doerId;
}

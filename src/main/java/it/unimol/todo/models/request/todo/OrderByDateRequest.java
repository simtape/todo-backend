package it.unimol.todo.models.request.todo;

import it.unimol.todo.models.OrderBy;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderByDateRequest {
    @NotBlank(message = "Order by is mandatory")
    private OrderBy orderBy;
}

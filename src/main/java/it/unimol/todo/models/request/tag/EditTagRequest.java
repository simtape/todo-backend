package it.unimol.todo.models.request.tag;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditTagRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;
}
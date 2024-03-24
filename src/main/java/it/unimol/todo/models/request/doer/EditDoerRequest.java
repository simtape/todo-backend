package it.unimol.todo.models.request.doer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditDoerRequest {
    private String name;
    private String surname;
    private String email;
    private long tagId;
}

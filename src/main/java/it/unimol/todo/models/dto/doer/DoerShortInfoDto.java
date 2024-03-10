package it.unimol.todo.models.dto.doer;

import it.unimol.todo.models.entities.Doer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoerShortInfoDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    public DoerShortInfoDto(Doer doer) {
        this.id = doer.getId();
        this.name = doer.getName();
        this.surname = doer.getSurname();
        this.email = doer.getEmail();
    }
}

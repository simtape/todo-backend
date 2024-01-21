package it.unimol.todo.models.dto.doer;

import it.unimol.todo.models.entities.Doer;

public class DoerShortInfoDto {
    private String name;
    private String surname;
    private String email;
    private int todoCompletedCount;
    private int todoNotCompletedCount;

    public DoerShortInfoDto(Doer doer, int todoCompletedCount, int todoNotCompletedCount) {
        this.name = doer.getName();
        this.surname = doer.getSurname();
        this.email = doer.getEmail();
        this.todoCompletedCount = todoCompletedCount;
        this.todoNotCompletedCount = todoNotCompletedCount;
    }
}

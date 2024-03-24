package it.unimol.todo.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private Long id;

    private String name;

    private Instant createdOn;

    private Instant updatedOn;
}

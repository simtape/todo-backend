package it.unimol.todo.repositories;

import it.unimol.todo.models.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}

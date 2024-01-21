package it.unimol.todo.services;

import it.unimol.todo.models.dto.TodoDto;
import it.unimol.todo.models.dto.TodoShortInfoDto;
import it.unimol.todo.models.entities.Todo;
import it.unimol.todo.models.request.EditTodoRequest;
import it.unimol.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void create(EditTodoRequest editTodoRequest) {
        Todo todo = new Todo();
        todo.setTitle(editTodoRequest.getTitle());
        todo.setDescription(editTodoRequest.getDescription());
        todo.setCompleted(false);
        this.todoRepository.save(todo);
    }

    public TodoDto read(Long id) {
        Todo todo = getTodoById(id);
        return new TodoDto(todo);
    }

    public List<TodoShortInfoDto> read() {
        List<Todo> todos = this.todoRepository.findAll();

        return todos.stream()
                .map(TodoShortInfoDto::new)
                .toList();
    }

    public void delete(Long id) {
        Todo todo = getTodoById(id);
        this.delete(todo);
    }

    public void update(Long id, EditTodoRequest editTodoRequest) {
        Todo todo = getTodoById(id);

        if(todo.getCompleted())
            throw new RuntimeException("Cannot edit a completed to-do.");

        todo.setTitle(editTodoRequest.getTitle());
        todo.setDescription(editTodoRequest.getDescription());
        this.save(todo);
    }

    public void changeStatus(Long id) {
        Todo todo = getTodoById(id);
        if(todo.getCompleted())
            todo.setCompletedOn(null);
        else
            todo.setCompletedOn(new java.sql.Timestamp(System.currentTimeMillis()));

        todo.setCompleted(!todo.getCompleted());
        this.save(todo);
    }

    /**
     * Use this method to get a to-do by its id.
     */
    private Todo getTodoById(Long id) {
        return this.todoRepository.findById(id).orElseThrow();
    }

    /**
     * Use this method to save a new to-do
     * or to update an existing one.
     */
    private void save(Todo todo) {
        this.todoRepository.save(todo);
    }

    /**
     * Use this method to delete a to-do.
     */
    private void delete(Todo todo) {
        this.todoRepository.delete(todo);
    }
}
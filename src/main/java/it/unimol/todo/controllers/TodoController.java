package it.unimol.todo.controllers;

import it.unimol.todo.models.dto.TodoDto;
import it.unimol.todo.models.request.EditTodoRequest;
import it.unimol.todo.models.response.Response;
import it.unimol.todo.services.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("")
    public Response<?> create(@Valid @RequestBody EditTodoRequest editTodoRequest){
        this.todoService.create(editTodoRequest);
        return new Response<>();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response<TodoDto> read(@PathVariable Long id){
        return new Response<>(this.todoService.read(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Response<?> update(@PathVariable Long id, @Valid @RequestBody EditTodoRequest editTodoRequest){
        this.todoService.update(id, editTodoRequest);
        return new Response<>();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public Response<?> delete(@PathVariable Long id){
        this.todoService.delete(id);
        return new Response<>();
    }
}
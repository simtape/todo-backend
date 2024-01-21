package it.unimol.todo.controllers;

import it.unimol.todo.models.dto.todo.TodoDto;
import it.unimol.todo.models.dto.todo.TodoShortInfoDto;
import it.unimol.todo.models.request.todo.PatchTodoPriorityRequest;
import it.unimol.todo.models.request.todo.EditTodoRequest;
import it.unimol.todo.models.response.Response;
import it.unimol.todo.services.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Response<?> create(@Valid @RequestBody EditTodoRequest editTodoRequest) {
        this.todoService.createTodo(editTodoRequest);
        return new Response<>();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response<List<TodoShortInfoDto>> read() {
        return new Response<>(this.todoService.readTodo());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response<TodoDto> read(@PathVariable Long id) {
        return new Response<>(this.todoService.readTodo(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Response<?> update(@PathVariable Long id, @Valid @RequestBody EditTodoRequest editTodoRequest) {
        this.todoService.updateTodo(id, editTodoRequest);
        return new Response<>();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public Response<?> delete(@PathVariable Long id) {
        this.todoService.deleteTodo(id);
        return new Response<>();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/priority")
    public Response<?> patchPriority(@PathVariable Long id,
                                     @Valid @RequestBody PatchTodoPriorityRequest patchTodoPriorityRequest) {
        this.todoService.patchTodoPriority(id, patchTodoPriorityRequest);
        return new Response<>();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/completed")
    public Response<?> patchCompletedStatus(@PathVariable Long id) {
        this.todoService.patchTodoCompletedStatus(id);
        return new Response<>();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/starred")
    public Response<?> patchStarredStatus(@PathVariable Long id) {
        this.todoService.patchTodoStarredStatus(id);
        return new Response<>();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/test-enc")
    public Response<String> test() {
        return new Response<>(this.todoService.encrypt("test"));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/test-dec")
    public Response<String> testDec() {
        return new Response<>(this.todoService.decrypt());
    }
}
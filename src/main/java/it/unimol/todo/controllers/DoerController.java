package it.unimol.todo.controllers;

import it.unimol.todo.models.dto.doer.DoerDto;
import it.unimol.todo.models.dto.doer.DoerShortInfoDto;
import it.unimol.todo.models.dto.todo.TodoDto;
import it.unimol.todo.models.dto.todo.TodoShortInfoDto;
import it.unimol.todo.models.request.doer.EditDoerRequest;
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
@RequestMapping("/doer")
public class DoerController {
    private final TodoService todoService;

    @Autowired
    public DoerController(TodoService todoService) {
        this.todoService = todoService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("")
    public Response<?> create(@Valid @RequestBody EditDoerRequest editDoerRequest) {
        this.todoService.createDoer(editDoerRequest);
        return new Response<>();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response<List<DoerShortInfoDto>> read() {
        return new Response<>(this.todoService.readDoer());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response<DoerDto> read(@PathVariable Long id) {
        return new Response<>(this.todoService.readDoer(id));
    }

    // delete endpoint
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public Response<?> delete(@PathVariable Long id) {
        this.todoService.deleteDoer(id);
        return new Response<>();
    }
}
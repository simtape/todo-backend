package it.unimol.todo.controllers;

import it.unimol.todo.models.dto.tag.TagDto;
import it.unimol.todo.models.dto.tag.TagShortInfoDto;
import it.unimol.todo.models.request.tag.EditTagRequest;
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
@RequestMapping("/tag")
public class TagController {
    private final TodoService todoService;

    @Autowired
    public TagController(TodoService todoService) {
        this.todoService = todoService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("")
    public Response<?> create(@Valid @RequestBody EditTagRequest editTagRequest) {
        this.todoService.createTag(editTagRequest);
        return new Response<>();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response<List<TagShortInfoDto>> read() {
        return new Response<>(this.todoService.readTag());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response<TagDto> read(@PathVariable Long id) {
        return new Response<>(this.todoService.readTag(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Response<?> update(@PathVariable Long id, @Valid @RequestBody EditTagRequest editTagRequest) {
        this.todoService.updateTag(id, editTagRequest);
        return new Response<>();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public Response<?> delete(@PathVariable Long id) {
        this.todoService.deleteTag(id);
        return new Response<>();
    }
}
package it.unimol.todo.controllers;

import it.unimol.todo.services.TodoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/todo")
public class DoerController {
   /* private final TodoService todoService;*/

}

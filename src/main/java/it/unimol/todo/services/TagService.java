package it.unimol.todo.services;

import it.unimol.todo.models.dto.TodoShortInfoDto;
import it.unimol.todo.models.entities.Tag;
import it.unimol.todo.models.entities.Todo;
import it.unimol.todo.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<TodoShortInfoDto> readTodosByTag(Long id) {
        Tag tag = this.getTagById(id);
        List<Todo> groups = tag.getTodos();

        return groups.stream()
                .map(TodoShortInfoDto::new)
                .toList();
    }

    public Tag getTagById(Long id) {
        return this.tagRepository.findById(id).orElseThrow();
    }
}

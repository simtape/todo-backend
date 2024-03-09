package it.unimol.todo.services;

import it.unimol.todo.models.Priority;
import it.unimol.todo.models.dto.doer.DoerDto;
import it.unimol.todo.models.dto.doer.DoerShortInfoDto;
import it.unimol.todo.models.dto.tag.TagDto;
import it.unimol.todo.models.dto.tag.TagShortInfoDto;
import it.unimol.todo.models.dto.todo.TodoDto;
import it.unimol.todo.models.dto.todo.TodoShortInfoDto;
import it.unimol.todo.models.entities.Doer;
import it.unimol.todo.models.entities.Tag;
import it.unimol.todo.models.entities.Todo;
import it.unimol.todo.models.request.doer.EditDoerRequest;
import it.unimol.todo.models.request.tag.EditTagRequest;
import it.unimol.todo.models.request.todo.EditTodoRequest;
import it.unimol.todo.models.request.todo.PatchTodoPriorityRequest;
import org.apache.commons.crypto.stream.CryptoInputStream;
import org.apache.commons.crypto.stream.CryptoOutputStream;
import org.apache.commons.crypto.utils.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static it.unimol.todo.models.Priority.LOW;
import static it.unimol.todo.models.Priority.MEDIUM;

@Service
public class TodoService {
    private List<Todo> todos = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private List<Doer> doers = new ArrayList<>();
    private long todoIdCounter;
    private long doerIdCounter;
    private long tagIdCounter;

    private final SecretKeySpec key = AES.newSecretKeySpec(getUTF8Bytes("1234567890123456"));
    private final IvParameterSpec iv = new IvParameterSpec(getUTF8Bytes("1234567890123456"));
    private final String transform = AES.CBC_PKCS5_PADDING;
    private static final String TEXT_REGEX = "";
    private static final String NAME_AND_SURNAME_REGEX = "";
    private ByteArrayOutputStream outputStream;

    @Autowired()
    public TodoService() {
        this.todoIdCounter = 0;
        this.doerIdCounter = 0;
        this.tagIdCounter = 0;
    }

    // to-do CRUD operations

    public void createTodo(EditTodoRequest editTodoRequest) {
        if (isNotValidDescription(editTodoRequest.getDescription())) {
            throw new RuntimeException("Can't create todo without description.");
        }

        if (!isValidTitle(editTodoRequest.getTitle())) {
            throw new RuntimeException("Can't create todo without title.");
        }

        if (tags.isEmpty()) {
            throw new RuntimeException("Add at least a tag before creating a todo!");
        }

        Tag tag = this.tags.stream()
                .filter(tag1 -> tag1.getId().equals(editTodoRequest.getTagId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tag not found."));

        Todo todo = new Todo();
        todo.setId(this.todoIdCounter++);

        todo.setTitle(editTodoRequest.getTitle());
        todo.setDescription(editTodoRequest.getDescription());
        todo.setPriority(editTodoRequest.getPriority());
        todo.setTag(tag);
        todo.setCompleted(false);
        todo.setStarred(false);
        todo.setCreatedOn(Instant.now());
        todo.setUpdatedOn(Instant.now());

        this.todos.add(todo);
    }

    public TodoDto readTodo(Long id) {
        return this.todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .map(TodoDto::new)
                .orElseThrow(() -> new RuntimeException("Todo not found."));
    }

    public List<TodoShortInfoDto> readTodo() {
        return todos.stream()
                .map(TodoShortInfoDto::new)
                .toList();
    }

    public void deleteTodo(Long id) {
        this.todos.removeIf(todo -> todo.getId().equals(id));
    }

    public void updateTodo(Long id, EditTodoRequest editTodoRequest) {
        this.todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                        todo -> {
                            todo.setTitle(editTodoRequest.getTitle());
                            todo.setDescription(editTodoRequest.getDescription());
                            todo.setUpdatedOn(Instant.now());
                            todo.setPriority(editTodoRequest.getPriority());
                        },
                        () -> {
                            throw new RuntimeException("Todo not found.");
                        }
                );
    }

    public void patchTodoCompletedStatus(Long id) {
        this.todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                        todo -> {
                            todo.setCompleted(!todo.getCompleted());
                        },
                        () -> {
                            throw new RuntimeException("Todo not found.");
                        }
                );
    }

    public void patchTodoStarredStatus(Long id) {
        this.todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                        todo -> {
                            todo.setCompleted(!todo.getCompleted());
                        },
                        () -> {
                            throw new RuntimeException("Todo not found.");
                        }
                );
    }

    public void patchTodoPriority(Long id, PatchTodoPriorityRequest patchTodoPriorityRequest) {
        this.todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                        todo -> {
                            todo.setPriority(patchTodoPriorityRequest.getPriority());
                        },
                        () -> {
                            throw new RuntimeException("Todo not found.");
                        }
                );
    }

    public List<Todo> readTodosByDefaultOrder() {
        return this.todos.stream()
                .sorted(this::compareStarred)
                .sorted(this::comparePriority)
                .toList();
    }

    private int compareStarred(Todo todo1, Todo todo2) {
        if (todo1.getStarred() && todo2.getStarred()) {
            return todo1.getPriority().compareTo(todo2.getPriority());
        } else if (todo1.getStarred()) {
            return -1;
        } else if (todo2.getStarred()) {
            return 1;
        } else {
            return todo1.getPriority().compareTo(todo2.getPriority());
        }
    }

    private int comparePriority(Todo todo1, Todo todo2) {
        if (this.isPriorityBiggerThanAnother(todo1.getPriority(), todo2.getPriority())) {
            return 1;
        } else if (this.isPriorityBiggerThanAnother(todo2.getPriority(), todo1.getPriority())) {
            return -1;
        } else {
            return 0;
        }
    }

    private Boolean isPriorityBiggerThanAnother(Priority priority1, Priority priority2) {
        return switch (priority1) {
            case MEDIUM -> priority2 == LOW;
            case HIGH -> priority2 == LOW || priority2 == MEDIUM;
            default -> false;
        };
    }

    // tag CRUD operations
    public void createTag(EditTagRequest editTagRequest) {
        if (!isTagNameValid(editTagRequest.getName())) {
            throw new RuntimeException("Invalid tag name.");
        }

        Tag tag = new Tag();
        tag.setId(this.tagIdCounter++);
        tag.setName(editTagRequest.getName());
        tag.setCreatedOn(Instant.now());
        tag.setUpdatedOn(Instant.now());
        this.tags.add(tag);
    }

    public void updateTag(Long id, EditTagRequest editTagRequest) {
        if (!isTagNameValid(editTagRequest.getName())) {
            throw new RuntimeException("Invalid tag name.");
        }

        this.tags.stream()
                .filter(tag -> tag.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                        tag -> {
                            tag.setName(editTagRequest.getName());
                            tag.setUpdatedOn(Instant.now());
                        },
                        () -> {
                            throw new RuntimeException("Tag not found.");
                        }
                );
    }

    public TagDto readTag(Long id) {
        return this.tags.stream()
                .filter(tag -> tag.getId().equals(id))
                .findFirst()
                .map(TagDto::new)
                .orElseThrow(() -> new RuntimeException("Tag not found."));
    }

    public List<TagShortInfoDto> readTag() {
        return this.tags.stream()
                .map(TagShortInfoDto::new)
                .toList();
    }

    public boolean isTagNameValid(String name) {
        Pattern pattern = Pattern.compile(NAME_AND_SURNAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        return name.length() <= 10 && !name.trim().isEmpty() /*&& matcher.matches()*/;
    }

    public void deleteTag(Long id) {
        Tag tagToDelete = this.tags.stream()
                .filter(tag -> tag.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tag not found."));

        this.todos.stream().filter(todo -> todo.getTag().equals(tagToDelete)).findFirst()
                .ifPresentOrElse(
                        todo -> {
                            throw new RuntimeException("Tag is used by a todo.");
                        },
                        () -> {
                            this.tags.removeIf(tag -> tag.getId().equals(id));
                        }
                );
    }

    public List<TodoShortInfoDto> getTodosByDate(Instant date) {
        return this.todos.stream()
                .filter(todo -> todo.getCreatedOn().equals(date))
                .map(TodoShortInfoDto::new)
                .toList();
    }

    // doer CRUD operations
    public void createDoer(EditDoerRequest editDoerRequest) {
        if (!isValidDoerName(editDoerRequest.getName())) {
            throw new RuntimeException("Invalid doer name");
        }

        if (!isValidDoerSurname(editDoerRequest.getSurname())) {
            throw new RuntimeException("Invalid doer surname");
        }

        Doer doer = new Doer();
        doer.setId(doerIdCounter);
        doer.setName(editDoerRequest.getName());
        doer.setSurname(editDoerRequest.getSurname());
        doer.setEmail(editDoerRequest.getEmail());

        doerIdCounter++;

        doers.add(doer);
    }

    public DoerDto readDoer(Long id) {
        return this.doers.stream()
                .filter(doer -> doer.getId().equals(id))
                .findFirst()
                .map(DoerDto::new)
                .orElseThrow(() -> new RuntimeException("Doer not found."));
    }

    public List<DoerShortInfoDto> readDoer() {
        return doers.stream()
                .map(DoerShortInfoDto::new)
                .toList();
    }


    // input validity
    public boolean isNotValidDescription(String description) {
        Pattern pattern = Pattern.compile(TEXT_REGEX);
        Matcher matcher = pattern.matcher(description);

        return description.length() >= 100 || description.trim().isEmpty() /*&& matcher.matches()*/;
    }

    public boolean isValidTitle(String title) {
        return title.length() <= 20 && !title.trim().isEmpty();
    }

    public boolean isValidDoerName(String name) {
        Pattern pattern = Pattern.compile(NAME_AND_SURNAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        return name.length() <= 50 && !name.trim().isEmpty() /*&& matcher.matches()*/;
    }

    public boolean isValidDoerSurname(String surname) {
        Pattern pattern = Pattern.compile(NAME_AND_SURNAME_REGEX);
        Matcher matcher = pattern.matcher(surname);
        return surname.length() <= 100 && !surname.trim().isEmpty() /*&& matcher.matches()*/;
    }

    // encryption
    public String encrypt(String plainText) {
        Properties properties = new Properties();
        //Encryption with CryptoOutputStream.

        this.outputStream = new ByteArrayOutputStream();

        try (CryptoOutputStream cos = new CryptoOutputStream(transform, properties, outputStream, key, iv)) {
            cos.write(getUTF8Bytes(plainText));
            cos.flush();

            return Arrays.toString(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String decrypt() {
        Properties properties = new Properties();

        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        try (CryptoInputStream cis = new CryptoInputStream(transform, properties, inputStream, key, iv)) {
            byte[] decryptedData = new byte[1024];
            int decryptedLen = 0;
            int i;
            while ((i = cis.read(decryptedData, decryptedLen, decryptedData.length - decryptedLen)) > -1) {
                decryptedLen += i;
            }

            return new String(decryptedData, 0, decryptedLen, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] getUTF8Bytes(final String input) {
        return input.getBytes(StandardCharsets.UTF_8);
    }

}
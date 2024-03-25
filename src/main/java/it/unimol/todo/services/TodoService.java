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
import it.unimol.todo.models.request.todo.AssignTodoRequest;
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
        throw new RuntimeException("Create todo functionality: not implemented yet.");
    }

    public TodoDto readTodo(Long id) {
        throw new RuntimeException("Read todo functionality: not implemented yet.");
    }

    public List<TodoShortInfoDto> readTodo() {
        throw new RuntimeException("Read todo functionality: not implemented yet.");
    }

    public void deleteTodo(Long id) {
        throw new RuntimeException("Delete todo functionality: not implemented yet.");
    }

    public void updateTodo(Long id, EditTodoRequest editTodoRequest) {
        throw new RuntimeException("Update todo functionality: not implemented yet.");
    }

    public void patchTodoCompletedStatus(Long id) {
        throw new RuntimeException("Patch todo completed status functionality: not implemented yet.");
    }

    public void patchTodoStarredStatus(Long id) {
        throw new RuntimeException("Patch todo starred status functionality: not implemented yet.");
    }

    public void patchTodoPriority(Long id, PatchTodoPriorityRequest patchTodoPriorityRequest) {
        throw new RuntimeException("Patch todo priority functionality: not implemented yet.");
    }

    public void assignTodo(AssignTodoRequest assignTodoRequest){
        throw new RuntimeException("Assign todo functionality: not implemented yet.");
    }

    public List<Todo> readTodosByDefaultOrder() {
        throw new RuntimeException("Read todos by default order functionality: not implemented yet.");
       
    }

    private int compareStarred(Todo todo1, Todo todo2) {
       throw new RuntimeException("Compare starred functionality: not implemented yet.");
    }

    private int comparePriority(Todo todo1, Todo todo2) {
    
        throw new RuntimeException("Compare priority functionality: not implemented yet.");
    }

    private Boolean isPriorityBiggerThanAnother(Priority priority1, Priority priority2) {
        
        throw new RuntimeException("Is priority bigger than another functionality: not implemented yet.");
    }

    // tag CRUD operations
    public void createTag(EditTagRequest editTagRequest) {
        throw new RuntimeException("Create tag functionality: not implemented yet.");
    }

    public void updateTag(Long id, EditTagRequest editTagRequest) {
        throw new RuntimeException("Update tag functionality: not implemented yet.");
    }

    public TagDto readTag(Long id) {
        throw new RuntimeException("Read tag functionality: not implemented yet.");
    }

    public List<TagShortInfoDto> readTag() {
        throw new RuntimeException("Read tag functionality: not implemented yet.");
    }

    public boolean isTagNameValid(String name) {
        throw new RuntimeException("Is tag name valid functionality: not implemented yet.");
    }

    public void deleteTag(Long id) {
        throw new RuntimeException("Delete tag functionality: not implemented yet.");
    }

    public List<TodoShortInfoDto> getTodosByDate(Instant date) {
        throw new RuntimeException("Get todos by date functionality: not implemented yet.");
    }

    // doer CRUD operations
    public void createDoer(EditDoerRequest editDoerRequest) {
       throw new RuntimeException("Create doer functionality: not implemented yet.");
    }

    public DoerDto readDoer(Long id) {
        throw new RuntimeException("Read doer functionality: not implemented yet.");
    }

    public List<DoerShortInfoDto> readDoer() {
        throw new RuntimeException("Read doer functionality: not implemented yet.");
    }

    public void updateDoer(long id, EditDoerRequest editDoerRequest){
        throw new RuntimeException("Update doer functionality: not implemented yet.");

    }

    public void deleteDoer(Long id) {
        throw new RuntimeException("Delete doer functionality: not implemented yet.");
    }

    // input validity
    public boolean isNotValidDescription(String description) {

        throw new RuntimeException("Is not valid description functionality: not implemented yet.");
    }

    public boolean isValidTitle(String title) {
        throw new RuntimeException("Is valid title functionality: not implemented yet.");
    }

    public boolean isValidDoerName(String name) {
    
        throw new RuntimeException("Is valid doer name functionality: not implemented yet.");
    }

    public boolean isValidDoerSurname(String surname) {
        throw new RuntimeException("Is valid doer surname functionality: not implemented yet.");
    }

    // encryption
    public String encrypt(String plainText) {
        return null;
    }

    public String decrypt() {
        return null;
    }

    private static byte[] getUTF8Bytes(final String input) {
        return null;
    }

    public void patchDoerTag(Long id, EditDoerRequest editDoerRequest) {
        throw new RuntimeException("Patch doer tag functionality: not implemented yet.");
    }
}
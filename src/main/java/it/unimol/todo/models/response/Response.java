package it.unimol.todo.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.unimol.todo.utils.Messages;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private T result;
    private String message;

    public Response() {
        this.message = Messages.OK;
    }

    public Response(T result) {
        this.result = result;
        this.message = Messages.OK;
    }

    public Response(T result, String message) {
        this.result = result;
        this.message = message;
    }

    public static Response<?> message(String message) {
        return new Response<>(null, message);
    }

}
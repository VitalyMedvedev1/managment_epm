package ru.homework.andry.soap.element.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Arrays;

@Setter
@Getter
public class ErrorResponse {
    public static final String ERROR_LEVEL = "ERROR";
    private String level;
    private String message;
    private LocalDateTime dateTime;
    private String[] stacktrace;

    public static ErrorResponse build(String message, StackTraceElement[] stacktrace) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setLevel(ERROR_LEVEL);
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(message);

        if (stacktrace != null) {
            errorResponse.setStacktrace(
                    Arrays.stream(stacktrace)
                          .map(StackTraceElement::toString).
                                  toArray(String[]::new));
        }
        return errorResponse;
    }
}
package ru.homework.andry.soap.builder.impl;

import io.dliga.micro.employee_web_service.Status;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class StatusResponseBuilder {
    private static int errorCode;
    private static String errorMessage = "";

    public static Status build(int code, String message) {
        Status status = new Status();
        status.setErrorCode(code);
        status.setErrorMessage(message);
        return status;
    }

    public static Status build() {
        Status status = new Status();
        status.setErrorCode(errorCode);
        status.setErrorMessage(errorMessage);
        return status;
    }
}
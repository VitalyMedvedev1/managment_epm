package ru.homework.andry.soap.exeption;

import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException {

    public BusinessLogicException(String message) {
        super(message);
    }
}

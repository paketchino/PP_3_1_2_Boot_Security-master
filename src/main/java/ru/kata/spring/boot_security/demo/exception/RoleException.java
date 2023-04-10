package ru.kata.spring.boot_security.demo.exception;

public class RoleException extends RuntimeException{
    public RoleException() {
    }

    public RoleException(String message) {
        super(message);
    }
}

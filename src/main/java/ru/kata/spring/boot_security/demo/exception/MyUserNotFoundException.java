package ru.kata.spring.boot_security.demo.exception;

public class MyUserNotFoundException extends RuntimeException {

    public MyUserNotFoundException() {
    }

    public MyUserNotFoundException(String message) {
        super(message);
    }
}

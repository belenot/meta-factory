package com.belenot.util.exception;

@FunctionalInterface
public interface ExceptionHandler {
    void handle(Exception exc);
}
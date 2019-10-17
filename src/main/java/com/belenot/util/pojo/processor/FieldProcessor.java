package com.belenot.util.pojo.processor;

import java.lang.reflect.Field;

@FunctionalInterface
public interface FieldProcessor {
    Field process(Object pojo, Field field) throws IllegalAccessException;
    default boolean support(Field field) {
        // Made for @FunctionalInterface
        return true;
    }
}
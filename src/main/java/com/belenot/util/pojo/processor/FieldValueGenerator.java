package com.belenot.util.pojo.processor;

import java.lang.reflect.Field;

import com.belenot.util.pojo.support.ValueHolder;

@FunctionalInterface
public interface FieldValueGenerator {
    ValueHolder generate(Field field);
    default boolean support(Field field) {
        // Made for @FunctionalInterface
        return true;
    }
}
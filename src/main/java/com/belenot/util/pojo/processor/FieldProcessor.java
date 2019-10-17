package com.belenot.util.pojo.processor;

import java.lang.reflect.Field;

public interface FieldProcessor {
    Field process(Object pojo, Field field) throws IllegalAccessException;
    boolean support(Field field);
}
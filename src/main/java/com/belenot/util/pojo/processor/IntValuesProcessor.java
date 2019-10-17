package com.belenot.util.pojo.processor;

import java.lang.reflect.Field;

import com.belenot.util.pojo.annotation.RandomValues.IntValues;

public class IntValuesProcessor implements FieldProcessor {

    @Override
    public Field process(Object pojo, Field field) throws IllegalAccessException {
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        int[] values = field.getAnnotation(IntValues.class).value();
        int index = (int)(Math.random() * values.length);
        if (field.getType().equals(int.class)) {
            field.setInt(pojo, values[index]);
        } else if (field.getType().equals(Integer.class)) {
            field.set(pojo, Integer.valueOf(values[index]));
        }
        field.setAccessible(accessible);
        return field;
    }

    @Override
    public boolean support(Field field) {    
        return field.getAnnotation(IntValues.class) != null && (field.getType().equals(int.class) || field.getType().equals(Integer.class));
    }
    
}
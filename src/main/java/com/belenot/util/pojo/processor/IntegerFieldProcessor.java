package com.belenot.util.pojo.processor;

import java.lang.reflect.Field;

public class IntegerFieldProcessor implements FieldProcessor {
    private IntValuesProcessor intValuesProcessor = new IntValuesProcessor();

    @Override
    public Field process(Object pojo, Field field) throws IllegalAccessException {
        boolean accessable = field.isAccessible();
        field.setAccessible(true);
        if (intValuesProcessor.support(field)) {
            intValuesProcessor.process(pojo, field);
        } else {
            setRandomInt(pojo, field);
        }
        
        field.setAccessible(accessable);
        return field;
    }

    @Override
    public boolean support(Field field) {
        if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
            return true;
        }
        return false;
    }

    private Field setRandomInt(Object pojo, Field field) throws IllegalAccessException {
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        int value = (int)(Math.random() * Integer.MAX_VALUE);
        if (field.getType().equals(int.class)) {
            field.setInt(pojo, value);
        } else if (field.getType().equals(Integer.class)) {
            field.set(pojo, Integer.valueOf(value));
        }
        field.setAccessible(accessible);
        return field;
    }
}
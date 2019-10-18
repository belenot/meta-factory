package com.belenot.util.pojo.processor;

import java.lang.reflect.Field;
import java.util.List;

public class NumberFieldProcessor implements FieldProcessor {
    private NumberValuesProcessor numberValuesProcessor = new NumberValuesProcessor();

    @Override
    public Field process(Object pojo, Field field) throws IllegalAccessException {
        boolean accessable = field.isAccessible();
        field.setAccessible(true);
        if (numberValuesProcessor.support(field)) {
            numberValuesProcessor.process(pojo, field);
        } else {
            setRandomInt(pojo, field);
        }
        
        field.setAccessible(accessable);
        return field;
    }

    @Override
    public boolean support(Field field) {
        return isNumberType(field);
    }

    public static boolean isNumberType(Field field) {
        List<Class<?>> supportedTypes = List.of(Integer.class, int.class, Double.class, double.class, Long.class, long.class);
        if (supportedTypes.stream().anyMatch(type->type.equals(field.getType()))) {
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
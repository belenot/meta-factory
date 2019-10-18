package com.belenot.util.pojo.processor;

import java.lang.reflect.Field;

import com.belenot.util.pojo.annotation.RandomValues.NumberValues;

public class NumberValuesProcessor implements FieldProcessor {

    @Override
    public Field process(Object pojo, Field field) throws IllegalAccessException {
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        double[] values = field.getAnnotation(NumberValues.class).value();
        int index = (int)(Math.random() * values.length);
        if (field.getType().equals(int.class)) {
            field.setInt(pojo, (int)values[index]);
        } else if (field.getType().equals(Integer.class)) {
            field.set(pojo, Integer.valueOf((int)values[index]));
        } else if (field.getType().equals(double.class)) {
            field.setDouble(pojo, values[index]);
        } else if (field.getType().equals(Double.class)) {
            field.set(pojo, Double.valueOf(values[index]));
        } else if (field.getType().equals(long.class)) {
            field.setLong(pojo, (long)values[index]);
        } else if (field.getType().equals(Long.class)) {
            field.set(pojo, Long.valueOf((long)values[index]));
        }
        field.setAccessible(accessible);
        return field;
    }

    @Override
    public boolean support(Field field) {    
        return field.getAnnotation(NumberValues.class) != null && NumberFieldProcessor.isNumberType(field);
    }
    
}
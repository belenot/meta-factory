package com.belenot.util.pojo.processor;

import java.lang.reflect.Field;

import com.belenot.util.pojo.annotation.RandomValues.NumberValues;
import com.belenot.util.pojo.support.ValueHolder;

public class NumberValuesProcessor implements FieldValueGenerator {

    @Override
    public ValueHolder generate(Field field) {
        double[] values = field.getAnnotation(NumberValues.class).value();
        int index = (int)(Math.random() * values.length);
        if (field.getType().equals(int.class)) {
            return new ValueHolder((int)values[index]);
        } else if (field.getType().equals(Integer.class)) {
            return new ValueHolder(Integer.valueOf((int)values[index]));
        } else if (field.getType().equals(double.class)) {
            return new ValueHolder((double)values[index]);
        } else if (field.getType().equals(Double.class)) {
            return new ValueHolder(Double.valueOf((double)values[index]));
        } else if (field.getType().equals(long.class)) {
            return new ValueHolder((long)values[index]);
        } else if (field.getType().equals(Long.class)) {
            return new ValueHolder(Long.valueOf((long)values[index]));
        } else {
            return ValueHolder.empty();
        }
    }

    @Override
    public boolean support(Field field) {    
        return field.getAnnotation(NumberValues.class) != null && NumberFieldValueGenerator.isNumberType(field);
    }
    
}
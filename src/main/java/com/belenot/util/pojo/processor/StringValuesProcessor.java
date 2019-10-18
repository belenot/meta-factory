package com.belenot.util.pojo.processor;

import java.lang.reflect.Field;

import com.belenot.util.pojo.annotation.RandomValues.StringValues;
import com.belenot.util.pojo.support.ValueHolder;

public class StringValuesProcessor implements FieldValueGenerator {

    @Override
    public ValueHolder generate(Field field) {
        String[] strings = field.getAnnotation(StringValues.class).value();
        int index = (int)(Math.random() * strings.length);
        return new ValueHolder(strings[index]);
    }

    @Override
    public boolean support(Field field) {
        return field.getType().equals(String.class) && field.getDeclaredAnnotation(StringValues.class) != null;
    }
    
}
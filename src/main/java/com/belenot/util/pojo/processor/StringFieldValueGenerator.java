package com.belenot.util.pojo.processor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.belenot.util.pojo.support.ValueHolder;

public class StringFieldValueGenerator implements FieldValueGenerator {

    private List<FieldValueGenerator> fieldValueGeneratorRegistry = new ArrayList<>();
    {
        fieldValueGeneratorRegistry.add(new StringValuesProcessor());
        fieldValueGeneratorRegistry.add(new RandomStringProcessor());
    }

    @Override
    public ValueHolder generate(Field field) {
        for (FieldValueGenerator generator : fieldValueGeneratorRegistry) {
            if (generator.support(field)){
                return generator.generate(field);
            }
        }
        return new ValueHolder(generateRandomString());
    }

    @Override
    public boolean support(Field field) {
        return field.getType().equals(String.class);
    }

    private String generateRandomString() {
        int size = (int)(Math.random() * 5) + 5;
        StringBuilder builder = new StringBuilder(size);
        for (int i = 0; i < 10; i++) {
            char randomChar = (char)(Math.random() * (255-30)+30);
            builder.append(randomChar);
        }
        return builder.toString();
    }
    
}
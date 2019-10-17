package com.belenot.util.pojo.processor;

import java.lang.reflect.Field;

import com.belenot.util.pojo.annotation.Random;

public class DelegatingFieldProcessor implements FieldProcessor {

    @Override
    public Field process(Object pojo, Field field) throws IllegalAccessException {
        try {
            return field.getAnnotation(Random.class).processor().getDeclaredConstructor(new Class<?>[0]).newInstance(new Object[0]).process(pojo, field);
        } catch (Exception exc) {
            System.err.println(exc);
            return null;
        }
    }

    @Override
    public boolean support(Field field) {
        Random random = field.getAnnotation(Random.class);
        if (random != null && !random.processor().equals(StubFieldProcessor.class)) {
            return true;
        }
        return false;
    }

    @Deprecated(forRemoval = false, since="This is a stub")
    public static class StubFieldProcessor implements FieldProcessor {
        @Override
        public Field process(Object pojo, Field field) throws IllegalAccessException {
            throw new IllegalArgumentException("This is stub for " + Random.class.getCanonicalName() + " annotation default factory and should not be used for processing");
        }
        @Override
        public boolean support(Field field) {
            return false;
        }
        
    }
    
}
package com.belenot.util.pojo.processor;

import java.lang.reflect.Field;

import com.belenot.util.pojo.annotation.Random;
import com.belenot.util.pojo.support.ValueHolder;

public class DelegatingFieldProcessor implements FieldValueGenerator {

    @Override
    public ValueHolder generate(Field field) {
        try {
            return field.getAnnotation(Random.class).valueGenerator().getDeclaredConstructor(new Class<?>[0]).newInstance(new Object[0]).generate(field);
        } catch (Exception exc) {
            System.err.println(exc);
            return null;
        }
    }

    @Override
    public boolean support(Field field) {
        Random random = field.getAnnotation(Random.class);
        if (random != null && !random.valueGenerator().equals(StubFieldValueGenerator.class)) {
            return true;
        }
        return false;
    }

    @Deprecated(forRemoval = false, since="This is a stub")
    public static class StubFieldValueGenerator implements FieldValueGenerator {
        @Override
        public ValueHolder generate(Field field) {
            throw new IllegalArgumentException("This is stub for " + Random.class.getCanonicalName() + " annotation default factory and should not be used for processing");
        }
        @Override
        public boolean support(Field field) {
            return false;
        }
    }
    
}
package com.belenot.util.pojo.processor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.belenot.util.pojo.annotation.Random;
import com.belenot.util.pojo.support.ValueHolder;

public class RandomAnnotationPojoProcessor implements PojoProcessor {
    private List<FieldValueGenerator> fieldValueGeneratorRegistry = new ArrayList<>();
    private RandomAnnotationPojoProcessor() { }

    @Override
    public <T> T process(T pojo) {
        Class<? extends T> clazz = (Class<? extends T>)pojo.getClass();
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
            .filter(f -> f.isAnnotationPresent(Random.class))
            .collect(Collectors.toList());
        for (Field field : fields) {
            processField(field, pojo);
        }
        return pojo;
    }

    private void processField(Field field, Object pojo) {
        for (FieldValueGenerator fieldValueGenerator : fieldValueGeneratorRegistry) {
            if (fieldValueGenerator.support(field)) {
                try {
                    ValueHolder valueHolder = fieldValueGenerator.generate(field);
                    if (!valueHolder.getValueType().isAssignableFrom(field.getType())) throw new IllegalArgumentException("Generated value isn't assignable to field type");
                    setField(field, pojo, valueHolder);
                    break;
                } catch (IllegalAccessException exc) {
                    System.err.println(exc);
                }
            }
        }
    }

    private void setField(Field field, Object pojo, ValueHolder valueHolder) throws IllegalAccessException {
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        if (valueHolder.getValueType().equals(int.class)) {
            field.setInt(pojo, valueHolder.getIntValue());
        } else if (valueHolder.getValueType().equals(double.class)) {
            field.setDouble(pojo, valueHolder.getDoubleValue());
        } else if (valueHolder.getValueType().equals(long.class)) {
            field.setLong(pojo, valueHolder.getLongValue());
        } else {
            field.set(pojo, valueHolder.getObjectValue());
        }
        field.setAccessible(accessible);
    }

    public static Builder builder() {
        return new RandomAnnotationPojoProcessor().new Builder();
    }

    public class Builder {
        private Builder() {
            fieldValueGeneratorRegistry.add(new DelegatingFieldProcessor());
            fieldValueGeneratorRegistry.add(new NumberFieldValueGenerator());
            fieldValueGeneratorRegistry.add(new StringFieldValueGenerator());
        }
        public Builder addFieldValueGenerator(FieldValueGenerator generator) {
            fieldValueGeneratorRegistry.add(generator);
            return this;
        }
        public Builder addFieldValueGenerators(FieldValueGenerator... generators) {
            for (FieldValueGenerator generator : generators) {
                fieldValueGeneratorRegistry.add(generator);
            }
            return this;
        }
        public RandomAnnotationPojoProcessor build() {
            return RandomAnnotationPojoProcessor.this;
        }
    }
}
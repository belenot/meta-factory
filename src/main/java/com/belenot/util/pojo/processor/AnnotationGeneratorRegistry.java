package com.belenot.util.pojo.processor;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import com.belenot.util.pojo.annotation.RandomValues.NumberValues;

@Deprecated(forRemoval = false, since = "Not used yet")
public class AnnotationGeneratorRegistry {
    private static Map<Class<? extends Annotation>, FieldValueGenerator> registry = new HashMap<>();
    static {
        registry.put(NumberValues.class, new NumberValuesProcessor());
    }

    public static void add(Class<? extends Annotation> clazz, FieldValueGenerator generator) {
        registry.put(clazz, generator);
    }

    public static FieldValueGenerator get(Class<? extends Annotation> clazz) {
        return registry.get(clazz);
    }

}
package com.belenot.util.pojo.annotation;

import com.belenot.util.pojo.annotation.processor.GeneratedAnnotationProcessor;

import org.junit.platform.commons.util.ReflectionUtils;

public class ObjectGenerator {
    public static <T> T gen(Class<T> clazz) {
        T object = ReflectionUtils.newInstance(clazz);
        GeneratedAnnotationProcessor processor = new GeneratedAnnotationProcessor();
        processor.process(object);
        return object;
    }
}
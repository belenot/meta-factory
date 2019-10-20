package com.belenot.util.pojo.generator;

import com.belenot.util.pojo.annotation.processor.FactoriedAnnotationProcessor;

import org.junit.platform.commons.util.ReflectionUtils;

public class ObjectGenerator {
    public static <T> T gen(Class<T> clazz) {
        T object = ReflectionUtils.newInstance(clazz);
        FactoriedAnnotationProcessor processor = new FactoriedAnnotationProcessor();
        processor.process(object);
        return object;
    }
}
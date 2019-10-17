package com.belenot.util.pojo.processor.factory;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.belenot.util.pojo.annotation.Random;
import com.belenot.util.pojo.processor.PojoProcessor;
import com.belenot.util.pojo.processor.RandomAnnotationProcessor;

public class RandomAnnotationProcessorFactory implements PojoProcessorFactory {

    @Override
    public boolean support(Class<?> clazz) {
        List<Annotation> annotations = new ArrayList<>();
        annotations.addAll(List.of(clazz.getAnnotations()));
        annotations.addAll(Arrays.stream(clazz.getDeclaredFields()).map(f -> List.of(f.getAnnotations()))
                .reduce(new ArrayList<Annotation>(), (ac, arr) -> {
                    ac.addAll(arr);
                    return ac;
                }));
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Random.class)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public <T> PojoProcessor<T> getProcessor(T pojo, Class<T> clazz) {
        return new RandomAnnotationProcessor<>();
    }



}
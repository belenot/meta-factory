package com.belenot.util.pojo.processor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.belenot.util.pojo.annotation.Random;

public class RandomAnnotationProcessor<T> implements PojoProcessor<T> {
    private List<FieldProcessor> fieldProcessorRegistry = new ArrayList<>();
    {
        fieldProcessorRegistry.add(new IntegerFieldProcessor());
    }

    @Override
    public T process(T pojo, Class<T> clazz) {
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).filter(f -> f.isAnnotationPresent(Random.class))
            .collect(Collectors.toList());
        for (Field field : fields) {
            processField(field, pojo);
        }
        return pojo;
    }

    private Field processField(Field field, T pojo) {
        for (FieldProcessor fieldProcessor : fieldProcessorRegistry) {
            if (fieldProcessor.support(field)) {
                try {
                    return fieldProcessor.process(pojo, field);
                } catch (IllegalAccessException exc) {
                    System.err.println(exc);
                }
            }
        }
        return field;
    }


    
    
}
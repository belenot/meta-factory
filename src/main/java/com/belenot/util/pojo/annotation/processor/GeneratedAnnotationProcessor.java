package com.belenot.util.pojo.annotation.processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.belenot.util.pojo.Info;
import com.belenot.util.pojo.Place;
import com.belenot.util.pojo.annotation.Generated;
import com.belenot.util.pojo.generator.Generator;
import com.belenot.util.pojo.informator.Informator;

import org.junit.platform.commons.util.ReflectionUtils;

public class GeneratedAnnotationProcessor {
    public void process(Object object) {
        processFields(object, object.getClass().getDeclaredFields());
    }

    private void processFields(Object object, Field[] fields) {
        for (Field field : fields) {
            // if field annotated with @Generated
            if (field.isAnnotationPresent(Generated.class)) {
                Generated genAnnotation = field.getDeclaredAnnotation(Generated.class);
                Place place = new Place(object.getClass(), object, field);
                Info info = new Info(place, genAnnotation);
                Generator generator = ReflectionUtils.newInstance(genAnnotation.value());
                Object value = generator.generate(info);
                setValue(object, field, value);
            } 
            // if field is annotatated with meta annotations of @Generated
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                if (annotation.annotationType().isAnnotationPresent(Generated.class)) {
                    Generated genAnnotation = annotation.annotationType().getDeclaredAnnotation(Generated.class);
                    Place place = new Place(object.getClass(), object, field);
                    Informator informator = ReflectionUtils.newInstance(genAnnotation.informator());
                    Info info = informator.informate(genAnnotation);
                    if (info == null || info.getAttributes() == null)  {
                        info = new Info(place, annotation);
                    } else {
                        info = new Info(place, annotation, info.getAttributes());
                    }
                    info.setAnnotation(annotation);
                    info.setPlace(place);
                    Generator generator = ReflectionUtils.newInstance(genAnnotation.value());
                    Object value = generator.generate(info);
                    setValue(object, field, value);
                }
            }
        }
    }

    private void setValue(Object object, Field field, Object value) {
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            if (field.getType().equals(boolean.class)) {
                field.setBoolean(object, ((Boolean)value).booleanValue());
            } else if (field.getType().equals(byte.class)) {
                field.setByte(object, ((Byte)value).byteValue());
            } else if (field.getType().equals(short.class)) {
                field.setShort(object, ((Short)value).shortValue());
            } else if (field.getType().equals(char.class)) {
                field.setChar(object, ((Character)value).charValue());
            } else if (field.getType().equals(int.class)) {
                field.setInt(object, ((Integer)value).intValue());
            } else if (field.getType().equals(float.class)) {
                field.setFloat(object, ((Float)value).floatValue());
            } else if (field.getType().equals(double.class)) {
                field.setDouble(object, ((Double)value).doubleValue());
            } else if (field.getType().equals(long.class)) {
                field.setLong(object, ((Long)value).longValue());
            } else {
                field.set(object, value);
            }
        } catch (Exception exc) {
            throw new GenerationException(exc);
        }
    }
}

class GenerationException extends RuntimeException {
    public GenerationException() {
        super();
     }
    public GenerationException(Throwable throwable) {
        super(throwable);
    }
}
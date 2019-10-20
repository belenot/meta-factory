package com.belenot.util.pojo.annotation.processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.belenot.util.pojo.Info;
import com.belenot.util.pojo.Place;
import com.belenot.util.pojo.annotation.Factoried;
import com.belenot.util.pojo.generator.AbstractFactory;
import com.belenot.util.pojo.informator.Informator;

import org.junit.platform.commons.util.ReflectionUtils;

public class FactoriedAnnotationProcessor {
    public void process(Object object) {
        processFields(object, object.getClass().getDeclaredFields());
    }

    private void processFields(Object object, Field[] fields) {
        for (Field field : fields) {
            // if field annotated with @Generated
            if (field.isAnnotationPresent(Factoried.class)) {
                Factoried genAnnotation = field.getDeclaredAnnotation(Factoried.class);
                Place place = Place.of(object, field);
                Info info = Info.builder().place(place).annotation(genAnnotation).build();
                AbstractFactory generator = ReflectionUtils.newInstance(genAnnotation.value());
                Object value = generator.generate(info);
                setValue(object, field, value);
            } 
            // if field is annotatated with meta annotations of @Generated
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                if (annotation.annotationType().isAnnotationPresent(Factoried.class)) {
                    Factoried genAnnotation = annotation.annotationType().getDeclaredAnnotation(Factoried.class);
                    Place place = Place.of(object, field);
                    // Informator informator = ReflectionUtils.newInstance(genAnnotation.informator());
                    // Info info = informator.informate(genAnnotation);
                    Info info = Info.builder().annotation(annotation).place(place).type(getWrapper(field.getType())).build();
                    AbstractFactory generator = ReflectionUtils.newInstance(genAnnotation.value());
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

    private Class<?> getWrapper(Class<?> clazz) {
        if (clazz.equals(boolean.class)) return Boolean.FALSE.getClass();
        if (clazz.equals(short.class)) return Short.valueOf((short)0).getClass();
        if (clazz.equals(char.class)) return Character.valueOf((char)0).getClass();
        if (clazz.equals(int.class)) return Integer.valueOf(0).getClass();
        if (clazz.equals(long.class)) return Long.valueOf(0L).getClass();
        if (clazz.equals(float.class)) return Float.valueOf((float)0).getClass();
        if (clazz.equals(double.class)) return Double.valueOf((double)0).getClass();
        return clazz;
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

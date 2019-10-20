package com.belenot.util.pojo.generator.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import com.belenot.util.pojo.Info;
import com.belenot.util.pojo.annotation.Factoried;
import com.belenot.util.pojo.generator.AbstractFactory;

public class RandomNumberFactory implements AbstractFactory<Number> {
    @Factoried(RandomNumberFactory.class)
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RandomNumber {
        int max() default Integer.MAX_VALUE;
        int min() default 0;
    }

    @Override
    public Number generate(Info info) {
        int min = 0, max = Integer.MAX_VALUE;
        if (info.getAnnotation() instanceof RandomNumber) {
            RandomNumber randomNumber = (RandomNumber)info.getAnnotation();
            min = randomNumber.min();
            max = randomNumber.max();
        }
        Class<?> type = info.getType();
        double value = (Math.random() * (max - min) + min);
        if (type != null && Number.class.isAssignableFrom(type)) {
            return wrapValue(value, type);
        }
        return returnValue(value, (Field)info.getPlace().getAccessibleObject());
    }

    private Number returnValue(double value, Field field) {
        if (field.getType().equals(byte.class)) return (byte)value;
        if (field.getType().equals(short.class)) return (short)value;
        if (field.getType().equals(int.class)) return (int)value;
        if (field.getType().equals(float.class)) return (float)value;
        if (field.getType().equals(long.class)) return (long)value;
        return value;
    }

    private Number wrapValue(double value, Class<?> clazz) {
        if (clazz.equals(Byte.class)) return Byte.valueOf((byte)value);
        if (clazz.equals(Short.class)) return Short.valueOf((short)value);
        if (clazz.equals(Integer.class)) return Integer.valueOf((int)value);
        if (clazz.equals(Float.class)) return Float.valueOf((float)value);
        if (clazz.equals(Long.class)) return Long.valueOf((long)value);
        return Double.valueOf(value);
    }
}
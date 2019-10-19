package com.belenot.util.pojo.generator.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import com.belenot.util.pojo.Info;
import com.belenot.util.pojo.annotation.Generated;
import com.belenot.util.pojo.generator.Generator;

public class RandomNumberGenerator implements Generator {
    @Generated(RandomNumberGenerator.class)
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RandomNumber {
        int max() default Integer.MAX_VALUE;
        int min() default 0;
    }

    @Override
    public Object generate(Info info) {
        int min = 0, max = Integer.MAX_VALUE;
        if (info.getAnnotation() instanceof RandomNumber) {
            RandomNumber randomNumber = (RandomNumber)info.getAnnotation();
            min = randomNumber.min();
            max = randomNumber.max();
        }
        double value = (Math.random() * (max - min) + min);
        return returnValue(value, (Field)info.getPlace().getAccessibleObject());
    }

    private Object returnValue(double value, Field field) {
        if (field.getType().equals(byte.class)) return (byte)value;
        if (field.getType().equals(short.class)) return (short)value;
        if (field.getType().equals(int.class)) return (int)value;
        if (field.getType().equals(float.class)) return (float)value;
        if (field.getType().equals(long.class)) return (long)value;
        return value;
    }
}
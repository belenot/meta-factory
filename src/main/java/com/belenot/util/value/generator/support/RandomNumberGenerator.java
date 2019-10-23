package com.belenot.util.value.generator.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import com.belenot.util.value.annotation.Generated;
import com.belenot.util.value.generator.ValueGenerator;

public class RandomNumberGenerator implements ValueGenerator {

    @Generated(generator=RandomNumberGenerator.class)
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RandomNumber {
        double min() default Double.MIN_VALUE;
        double max() default Double.MAX_VALUE;
        Class<? extends Number> type() default Integer.class;
    }

    @Override
    public Object generate(Map<String, Object> info) {
        double min = Double.MIN_VALUE, max = Double.MAX_VALUE;
        Class<? extends Number> type = Integer.class;
        if (info.containsKey("meta") && info.get("meta") instanceof RandomNumber) {
            min = ((RandomNumber)info.get("meta")).min();
            max = ((RandomNumber)info.get("meta")).max();
            type = ((RandomNumber)info.get("meta")).type();
        }
        return Math.random()*(max-min)+min;
    }

    @Override
    public boolean support(Map<String, Object> info) {
        return true;
    }
    
}
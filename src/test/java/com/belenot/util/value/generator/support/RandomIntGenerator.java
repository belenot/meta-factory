package com.belenot.util.value.generator.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import com.belenot.util.value.annotation.Generated;
import com.belenot.util.value.generator.ValueGenerator;

public class RandomIntGenerator implements ValueGenerator {
    public static final int MIN = 0;
    public static final int MAX = Integer.MAX_VALUE;
    @Generated(generator=RandomIntGenerator.class)
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RandomInt {
        int min() default 0;
        int max() default Integer.MAX_VALUE;
    }

    @Override
    public Object generate(Map<String, Object> info) {
        int min = MIN, max = MAX;
        if (info.containsKey("meta") && info.get("meta") instanceof RandomInt) {
            RandomInt metaAnnotation = (RandomInt)info.get("meta");
            min = metaAnnotation.min();
            max = metaAnnotation.max();
        }
        return Integer.valueOf((int)(Math.random() * (max - min) + min));
    }
    @Override
    public boolean support(Map<String, Object> info) {
        return true;
    }
}
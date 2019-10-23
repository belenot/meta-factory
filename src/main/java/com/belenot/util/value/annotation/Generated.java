package com.belenot.util.value.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.belenot.util.value.generator.ValueGenerator;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Generated {
    String[] properties() default "";// For future
    Class<? extends ValueGenerator> generator() default ValueGenerator.class;
}
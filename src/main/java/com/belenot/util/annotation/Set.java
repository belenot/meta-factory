package com.belenot.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.belenot.util.accessor.Configurator;
import com.belenot.util.accessor.support.DefaultConfigurator;
import com.belenot.util.generator.ValueGenerator;
import com.belenot.util.generator.support.SimpleValueGenerator;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
public @interface Set {
    Class<? extends ValueGenerator> generator() default SimpleValueGenerator.class;
    Class<? extends Configurator> configurator() default DefaultConfigurator.class;
    Class<?> type() default Object.class;
}
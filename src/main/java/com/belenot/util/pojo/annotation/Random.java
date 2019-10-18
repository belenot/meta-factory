package com.belenot.util.pojo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.belenot.util.pojo.processor.DelegatingFieldProcessor.StubFieldValueGenerator;
import com.belenot.util.pojo.processor.FieldValueGenerator;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE, ElementType.METHOD})
public @interface Random {
    Class<? extends FieldValueGenerator> valueGenerator() default StubFieldValueGenerator.class;
}
package com.belenot.util.pojo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.belenot.util.pojo.processor.DelegatingFieldProcessor.StubFieldProcessor;
import com.belenot.util.pojo.processor.FieldProcessor;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE, ElementType.METHOD})
public @interface Random {
    Class<? extends FieldProcessor> processor() default StubFieldProcessor.class;
}
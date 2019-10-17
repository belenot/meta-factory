package com.belenot.util.pojo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.belenot.util.pojo.processor.factory.PojoProcessorFactory;
import com.belenot.util.pojo.processor.factory.RandomAnnotationProcessorFactory;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE, ElementType.METHOD})
public @interface Random {
    Class<? extends PojoProcessorFactory> factory() default RandomAnnotationProcessorFactory.class;
}
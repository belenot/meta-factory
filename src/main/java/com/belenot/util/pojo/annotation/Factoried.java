package com.belenot.util.pojo.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.belenot.util.pojo.generator.AbstractFactory;
import com.belenot.util.pojo.informator.Informator;
import com.belenot.util.pojo.informator.support.DefaultInformator;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.FIELD })
public @interface Factoried {
    Class<? extends AbstractFactory> value();
    Class<? extends Informator> informator() default DefaultInformator.class;
}
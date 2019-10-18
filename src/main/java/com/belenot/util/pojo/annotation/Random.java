package com.belenot.util.pojo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.belenot.util.pojo.processor.DelegatingFieldProcessor.StubFieldValueGenerator;
import com.belenot.util.pojo.processor.FieldValueGenerator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Random {
    Class<? extends FieldValueGenerator> valueGenerator() default StubFieldValueGenerator.class;

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface RandomString {
        public enum AllowedAlphabet {DEFAULT, ONLY_LETTERS, ONLY_NUMBERS, CUSTOM}
        String alphabet() default "abcdefghijklmnopqrstuvwxyz0123456789";
        AllowedAlphabet allowedAlphabet() default AllowedAlphabet.DEFAULT;
        int maxLength() default 10;
        int minLength() default 1;
    }
}
package com.belenot.util.pojo.generator.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.belenot.util.pojo.Info;
import com.belenot.util.pojo.annotation.Factoried;
import com.belenot.util.pojo.generator.AbstractFactory;

public class RandomStringFactory implements AbstractFactory<String> {

    @Factoried(RandomStringFactory.class)
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RandomString {
        int maxLength() default 10;
        int minLength() default 1;
        String alphabet() default "abcdefghijklmnopqrstuvwxyz0123456789";
    }

    @Override
    public String generate(Info info) {
        int maxLength = 10;
        int minLength = 1;
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
        if (info.getAnnotation() instanceof RandomString) {
            RandomString randomString = (RandomString)info.getAnnotation();
            maxLength = randomString.maxLength();
            minLength = randomString.minLength();
            alphabet = randomString.alphabet();
        }
        minLength = minLength < 0 ? 1 : minLength;
        maxLength = maxLength < minLength ? minLength : maxLength;
        int length = (int)(Math.random() * (maxLength - minLength) + minLength);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int)(Math.random() * (alphabet.length() - 1));
            builder.append(alphabet.charAt(index));
        }
        return builder.toString();
    }
    
}
package com.belenot.util.pojo.processor;

import java.lang.reflect.Field;

import com.belenot.util.pojo.annotation.Random.RandomString;
import com.belenot.util.pojo.support.ValueHolder;

public class RandomStringProcessor implements FieldValueGenerator {
    private static final String ONLY_LETTERS_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String ONLY_NUMBERS_ALPHABET = "0123456789";

    @Override
    public ValueHolder generate(Field field) {
        RandomString data = field.getAnnotation(RandomString.class);
        char[] alphabet = data.alphabet().toCharArray();
        int sub = data.maxLength()-data.minLength();
        int length = (int)(Math.random() * (sub>0?sub:0)) + data.minLength();
        switch (data.allowedAlphabet()) {
            case ONLY_LETTERS: ONLY_LETTERS_ALPHABET.toCharArray(); break;
            case ONLY_NUMBERS: ONLY_NUMBERS_ALPHABET.toCharArray(); break;
            default : break;
        }
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int)(Math.random() * (length-1));
            builder.append(alphabet[index]);
        }
        return new ValueHolder(builder.toString());
    }

    @Override
    public boolean support(Field field) {
        return field.getAnnotation(RandomString.class) != null;
    }

}
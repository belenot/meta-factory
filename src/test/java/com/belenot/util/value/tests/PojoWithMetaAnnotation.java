package com.belenot.util.value.tests;

import com.belenot.util.value.generator.support.RandomIntGenerator.RandomInt;

public class PojoWithMetaAnnotation {
    @RandomInt(min=50, max=55)
    private int value;
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
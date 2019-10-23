package com.belenot.util.value.tests;

import com.belenot.util.value.annotation.Generated;
import com.belenot.util.value.generator.support.RandomIntGenerator;

public class PojoWithIntValue {
    @Generated(generator = RandomIntGenerator.class)
    private int value;
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
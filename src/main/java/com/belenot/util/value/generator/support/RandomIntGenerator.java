package com.belenot.util.value.generator.support;

import java.util.Map;

import com.belenot.util.value.generator.ValueGenerator;

public class RandomIntGenerator implements ValueGenerator {

    @Override
    public Object generate(Map<String, Object> info) {
        return Integer.valueOf((int)(Math.random() * (Integer.MAX_VALUE - 1) + 1));
    }

    @Override
    public boolean support(Map<String, Object> info) {
        return true;
    }
    
}
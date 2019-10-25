package com.belenot.util.tests.generator;

import com.belenot.util.generator.GenerationInfo;
import com.belenot.util.generator.ValueGenerator;

public class Random100IntGenerator implements ValueGenerator {

    public Object generate(GenerationInfo info) {
        return (int)(Math.random() * 98) + 1;
    }
    
    
}
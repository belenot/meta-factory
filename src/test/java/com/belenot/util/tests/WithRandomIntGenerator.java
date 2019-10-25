package com.belenot.util.tests;

import com.belenot.util.annotation.Set;
import com.belenot.util.tests.generator.Random100IntGenerator;

public class WithRandomIntGenerator {
    @Set(generator = Random100IntGenerator.class)
    private int value;

    public int getValue() { return value; };
}
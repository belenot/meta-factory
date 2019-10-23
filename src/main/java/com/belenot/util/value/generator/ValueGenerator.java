package com.belenot.util.value.generator;

import java.util.Map;

public interface ValueGenerator {
    Object generate(Map<String, Object> info); // Recieves source, returns value
    boolean support(Map<String, Object> info); // Recieves source
}
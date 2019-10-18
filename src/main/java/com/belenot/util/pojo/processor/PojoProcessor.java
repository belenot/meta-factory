package com.belenot.util.pojo.processor;

@FunctionalInterface
public interface PojoProcessor {
    <T> T process(T pojo);
}
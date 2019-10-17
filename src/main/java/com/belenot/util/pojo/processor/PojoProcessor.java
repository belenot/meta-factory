package com.belenot.util.pojo.processor;

public interface PojoProcessor<T> {
    T process(T pojo, Class<T> clazz);
}
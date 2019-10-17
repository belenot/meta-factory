package com.belenot.util.pojo.processor;

@FunctionalInterface
public interface PojoProcessor<T> {
    T process(T pojo, Class<T> clazz);
    default T process(T pojo) {
        try {
            Class<T> clazz = (Class<T>)pojo.getClass();
            return process(pojo, clazz);
        } catch (Exception exc) {
            throw new IllegalStateException("Can't upcast " + pojo.getClass().getCanonicalName());
        }
    }
}
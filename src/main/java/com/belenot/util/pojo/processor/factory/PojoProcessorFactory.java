package com.belenot.util.pojo.processor.factory;

import java.lang.reflect.Method;

import com.belenot.util.pojo.processor.PojoProcessor;

@FunctionalInterface
public interface PojoProcessorFactory {
    <T> PojoProcessor<T> getProcessor(T pojo, Class<T> clazz);
    default boolean support(Class<?> clazz) {
        try {
            Method getProcessorMethod = this.getClass().getMethod("getProcessor", clazz, Class.class);
            if (getProcessorMethod != null) {
                return true;
            } else {
                return false;
            }
        } catch (NoSuchMethodException exc) {
            return false;
        }
    }
}
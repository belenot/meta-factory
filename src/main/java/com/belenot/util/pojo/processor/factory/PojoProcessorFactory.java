package com.belenot.util.pojo.processor.factory;

import com.belenot.util.pojo.processor.PojoProcessor;

public interface PojoProcessorFactory {
    <T> PojoProcessor<T> getProcessor(T pojo, Class<T> clazz);
    boolean support(Class<?> clazz);
}
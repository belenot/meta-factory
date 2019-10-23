package com.belenot.util.value.annotation.processor;

import java.lang.reflect.AnnotatedElement;
import java.util.List;

import com.belenot.util.value.ValueWrapper;

public abstract class AbstractGeneratedElementProcessor<T extends AnnotatedElement> {
    public void process(Object object) {
        List<T> generatedElements = getGeneratedElements(object);
        for (T generatedElement : generatedElements) {
            ValueWrapper valueWrapper = generateValue(generatedElement, object);
            if (isShouldSetValue()) {
                try {
                    Object value = convert(valueWrapper);
                    setValue(value, object, generatedElement);
                } catch (Exception exc) {
                    System.err.println(exc);
                }
            }
        }
    }
    public boolean support(Object object) {
        return getGeneratedElements(object).size() > 0;
    }
    protected abstract List<T> getGeneratedElements(Object object);
    protected abstract ValueWrapper generateValue(T generatedElement, Object object);
    protected final Object convert(ValueWrapper valueWrapper) {
        return valueWrapper.getValue();
    }
    protected abstract void setValue(Object value, Object object, T generatedElement) throws Exception;
    protected boolean isShouldSetValue() {
        return true;
    }
}
package com.belenot.util.pojo;

import java.util.ArrayList;
import java.util.List;

import com.belenot.util.pojo.processor.factory.PojoProcessorFactory;

public class PojoRandomGenerator {
    private List<PojoProcessorFactory> pojoProcessorFactoryRegistry = new ArrayList<>();

    public void addProcessorFactory(PojoProcessorFactory pojoProcessorFactory) {
        pojoProcessorFactoryRegistry.add(pojoProcessorFactory);
    }

    public <T> T generate(Class<T> clazz) {
        T pojo = null;
        try {
            pojo = clazz.getConstructor().newInstance();
        } catch (Exception exc) {
            System.err.println(exc);
        }

        return processAnnotations(pojo, clazz);
    }

    private <T> T processAnnotations(T pojo, Class<T> clazz) {
        for (PojoProcessorFactory pojoProcessorFactory : pojoProcessorFactoryRegistry) {
            if (pojoProcessorFactory.support(clazz)) {
                pojoProcessorFactory.getProcessor(pojo, clazz).process(pojo, clazz);
            }
        }
        return pojo;
    }
}
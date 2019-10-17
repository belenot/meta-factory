package com.belenot.util.pojo;

import java.util.ArrayList;
import java.util.List;

import com.belenot.util.pojo.processor.factory.PojoProcessorFactory;
import com.belenot.util.pojo.processor.factory.RandomAnnotationProcessorFactory;

public class PojoRandomGenerator {
    private List<PojoProcessorFactory> pojoProcessorFactoryRegistry = new ArrayList<>();

    public void addProcessorFactory(PojoProcessorFactory pojoProcessorFactory) {
        pojoProcessorFactoryRegistry.add(pojoProcessorFactory);
    }

    {
        addProcessorFactory(new RandomAnnotationProcessorFactory());
    }

    public <T> T generate(Class<T> clazz) {
        T pojo = null;
        try {
            //WHY I MUST PASS CLASS[0]?
            pojo = clazz.getDeclaredConstructor(new Class<?>[0]).newInstance(new Object[0]);
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
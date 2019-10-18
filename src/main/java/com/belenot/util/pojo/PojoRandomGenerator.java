package com.belenot.util.pojo;

import com.belenot.util.pojo.processor.RandomAnnotationProcessor;

public class PojoRandomGenerator {
    // private List<PojoProcessor> pojoProcessorRegistry = new ArrayList<>();

    // public void addProcessorFactory(PojoProcessorFactory pojoProcessorFactory) {
    //     pojoProcessorRegistry.add(pojoProcessorFactory);
    // }
        // {
        //     addProcessorFactory(new RandomAnnotationProcessorFactory());
        // }
    private RandomAnnotationProcessor pojoProcessor;
    public PojoRandomGenerator() {
        pojoProcessor = new RandomAnnotationProcessor();
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
        return pojoProcessor.process(pojo, clazz);
    }
}
package com.belenot.util.value;

import com.belenot.util.value.annotation.processor.support.GeneratedClassProcessor;
import com.belenot.util.value.annotation.processor.support.GeneratedFieldProcessor;

public class GeneratedProcessor {
    private GeneratedClassProcessor processor;
    {
        processor = processor.builder().processor(new GeneratedFieldProcessor()).build();
    }

    public void process(Object object) {
        processor.process(object);
    }
}
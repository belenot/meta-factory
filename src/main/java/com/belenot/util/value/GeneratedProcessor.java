package com.belenot.util.value;

import com.belenot.util.value.annotation.processor.support.DelegatingGeneratedElementProcessor;
import com.belenot.util.value.annotation.processor.support.GeneratedFieldProcessor;

public class GeneratedProcessor {
    private DelegatingGeneratedElementProcessor processor;
    {
        processor = processor.builder().processor(new GeneratedFieldProcessor()).build();
    }

    public void process(Object object) {
        processor.process(object);
    }
}
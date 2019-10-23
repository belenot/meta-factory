package com.belenot.util.value.annotation.processor.support;

import java.util.ArrayList;
import java.util.List;

import com.belenot.util.value.ValueWrapper;
import com.belenot.util.value.annotation.processor.AbstractGeneratedElementProcessor;

public class GeneratedClassProcessor extends AbstractGeneratedElementProcessor<Class<?>> {
    private List<AbstractGeneratedElementProcessor<?>> processors = new ArrayList<>();

    private GeneratedClassProcessor() {}
    
    @Override
    public void process(Object object) {
        for (AbstractGeneratedElementProcessor<?> processor : processors) {
            processor.process(object);
        }
    }

    public static Builder builder() {
        return new GeneratedClassProcessor().new Builder();
    }
    
    @Override
    protected ValueWrapper generateValue(Class<?> clazz, Object object) {
        return null;
    }

    @Override
    protected List<Class<?>> getGeneratedElements(Object object) {
        for (AbstractGeneratedElementProcessor<?> processor : processors) {
            if (processor.support(object))
                return List.of(object.getClass());
        }
        return List.of();
    }

    @Override
    protected void setValue(Object value, Object object, Class<?> clazz) throws Exception {
        // nothing to do
    }

    public class Builder {
        private Builder() {
            
        }

        public Builder processor(AbstractGeneratedElementProcessor<?> processor) {
            processors.add(processor);
            return this;
        }

        public GeneratedClassProcessor build() {
            return GeneratedClassProcessor.this;
        }
    }

    @Override
    protected boolean isShouldSetValue() {
        return false;
    }
 
}
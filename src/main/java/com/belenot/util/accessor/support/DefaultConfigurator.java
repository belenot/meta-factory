package com.belenot.util.accessor.support;

import com.belenot.util.accessor.Configurator;
import com.belenot.util.accessor.support.AbstractFieldSetter.FieldSetterStrategy;
import com.belenot.util.annotation.Set;
import com.belenot.util.converter.support.DumbConverter;
import com.belenot.util.generator.GenerationInfo;
import com.belenot.util.generator.support.SimpleValueGenerator;

public class DefaultConfigurator implements Configurator {

    public void configure(FieldSetterStrategy config) {
        try {
            config.generator(config.getField().getDeclaredAnnotation(Set.class).generator().getConstructor(new Class<?>[0]).newInstance(new Object[0]));
        } catch (Exception exc) {
            config.generator(new SimpleValueGenerator());
        }
        config.converter(new DumbConverter()).defaultValue(null).info(new GenerationInfo());
    }
    
}
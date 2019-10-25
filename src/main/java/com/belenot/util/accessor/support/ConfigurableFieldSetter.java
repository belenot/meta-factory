package com.belenot.util.accessor.support;

import java.lang.reflect.Field;

import com.belenot.util.annotation.Set;

public class ConfigurableFieldSetter extends AbstractFieldSetter {

    @Override
    protected FieldSetterStrategy configure(Object object, Field field) {
        Set s = field.getAnnotation(Set.class);
        FieldSetterStrategy strategy = new FieldSetterStrategy(object, field);
        try {
            s.configurator().getConstructor(new Class<?>[0]).newInstance(new Object[0]).configure(strategy);
        } catch (Exception exc) {
            new DefaultConfigurator().configure(strategy);
        }
        return strategy;
    }
    
}
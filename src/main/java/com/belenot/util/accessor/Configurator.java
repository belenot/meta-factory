package com.belenot.util.accessor;

import com.belenot.util.accessor.support.AbstractFieldSetter.FieldSetterStrategy;

public interface Configurator {
    void configure(FieldSetterStrategy config);
}
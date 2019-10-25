package com.belenot.util.accessor.support;

import java.lang.reflect.Field;
import java.util.Optional;

import com.belenot.util.FieldUtils;
import com.belenot.util.accessor.FieldSetter;
import com.belenot.util.converter.Converter;
import com.belenot.util.generator.GenerationInfo;
import com.belenot.util.generator.ValueGenerator;

public abstract class AbstractFieldSetter implements FieldSetter {


    public final void set(Object object, Field field, Object value) {
        FieldSetterStrategy strategy = configure(object, field);
        Object generatedValue = strategy.getGenerator().generate(strategy.getInfo());
        if (generatedValue==null) generatedValue = strategy.getDefaultValue();
        if (generatedValue==null) generatedValue = value;
        value = strategy.getConverter().convert(generatedValue);
        FieldUtils.setValue(object, field, value);
    }

    protected abstract FieldSetterStrategy configure(Object object, Field field);

    public static final class FieldSetterStrategy {
        private Object object;
        private Field field;
        private ValueGenerator generator;
        private GenerationInfo info;
        private Object defaultValue;
        private Converter converter;

        protected FieldSetterStrategy(Object object, Field field) {
            this.object = object;
            this.field = field;
        }

        public FieldSetterStrategy generator(ValueGenerator generator) {
            this.generator = generator;
            return this;
        }

        public FieldSetterStrategy info(GenerationInfo info) {
            this.info = info;
            return this;
        }

        public FieldSetterStrategy defaultValue(Object value) {
            this.defaultValue = value;
            return this;
        }

        public FieldSetterStrategy converter(Converter converter) {
            this.converter = converter;
            return this;
        }

        public Object getObject() {
            return object;
        }

        public Field getField() {
            return field;
        }

        private ValueGenerator getGenerator() {
            return generator;
        }

        private GenerationInfo getInfo() {
            return info;
        }

        private Object getDefaultValue() {
            return defaultValue;
        }

        private Converter getConverter() {
            return converter;
        }
    }
}
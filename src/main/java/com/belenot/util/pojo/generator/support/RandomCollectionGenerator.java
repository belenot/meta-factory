package com.belenot.util.pojo.generator.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.belenot.util.pojo.Info;
import com.belenot.util.pojo.annotation.Generated;
import com.belenot.util.pojo.generator.Generator;

import org.junit.platform.commons.util.ReflectionUtils;

public class RandomCollectionGenerator implements Generator {

    @Generated(RandomCollectionGenerator.class)
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RandomCollection {
        int maxSize() default 10;
        int minSize() default 1;
        Class<? extends Generator> itemGenerator();
        Class<? extends Collection> impl() default ArrayList.class;
        Class<?> itemType() default Object.class;
    }

    @Override
    public Object generate(Info info) {
        int maxSize = 10, minSize = 1;
        Generator itemGenerator = null;
        Collection<Object> impl = new ArrayList<>();
        Class<?> type = Object.class;
        if (info.getAnnotation() instanceof RandomCollection) {
            RandomCollection randomCollection = (RandomCollection)info.getAnnotation();
            maxSize = randomCollection.maxSize();
            minSize = randomCollection.minSize();
            itemGenerator = ReflectionUtils.newInstance(randomCollection.itemGenerator());
            type = randomCollection.itemType();
            try {
                impl = randomCollection.impl().getConstructor(new Class<?>[0]).newInstance(new Object[0]);
            } catch (Exception exc) { return null; }
        }
        if (itemGenerator != null) {
            int size = (int)(Math.random() * (maxSize - minSize) + minSize);
            for (int i = 0; i < size; i++) {
                Info itemInfo = Info.builder().from(info).attr("collection", impl).type(type).build();
                impl.add(itemGenerator.generate(itemInfo));
            }
            return impl;
        }
        return null;
    }
    
}
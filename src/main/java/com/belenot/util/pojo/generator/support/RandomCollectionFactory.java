package com.belenot.util.pojo.generator.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

import com.belenot.util.pojo.Info;
import com.belenot.util.pojo.annotation.Factoried;
import com.belenot.util.pojo.generator.AbstractFactory;

import org.junit.platform.commons.util.ReflectionUtils;

public class RandomCollectionFactory implements AbstractFactory<Collection> {

    @Factoried(RandomCollectionFactory.class)
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RandomCollection {
        int maxSize() default 10;
        int minSize() default 1;
        Class<? extends AbstractFactory> itemGenerator();
        Class<? extends Collection> impl() default ArrayList.class;
        Class<?> itemType() default Object.class;
    }

    @Override
    public Collection generate(Info info) {
        int maxSize = 10, minSize = 1;
        AbstractFactory itemGenerator = null;
        Collection impl = new ArrayList<>();
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
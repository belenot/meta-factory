package com.belenot.util.pojo.factory;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

import com.belenot.util.pojo.Info;
import com.belenot.util.pojo.annotation.Factoried;
import com.belenot.util.pojo.factory.RandomNumberFactory.RandomNumber;

public class RandomMapFactory implements AbstractFactory<Map> {

    @Factoried(RandomMapFactory.class)
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RandomMap {
        int maxSize() default 10;
        int minSize() default 1;
        Class<? extends AbstractFactory> itemGenerator();
        Class<? extends AbstractFactory> keyGenerator();
        Class<? extends Map> impl() default HashMap.class;
        Class<?> item() default Object.class;
        Class<?> key() default Object.class;
        // Annotation itemA() default @RandomNumber;
    }

    @Override
    public Map generate(Info info) {
        try {
            if (!(info.getAnnotation() instanceof RandomMap)) return null;
            RandomMap randomMap = (RandomMap)info.getAnnotation();
            int size = (int)(Math.random() *(randomMap.maxSize() - randomMap.minSize())+ randomMap.minSize());
            size = size < 0 ? 10 : size;
            AbstractFactory itemGenerator = randomMap.itemGenerator().getConstructor(new Class<?>[0]).newInstance(new Object[0]);
            AbstractFactory keyGenerator = randomMap.keyGenerator().getConstructor(new Class<?>[0]).newInstance(new Object[0]);
            Map impl = randomMap.impl().getConstructor(new Class<?>[0]).newInstance(new Object[0]);
            for (int i = 0; i < size; i++) {
                Info itemInfo = Info.builder().type(randomMap.item()).attr("map", impl).build();
                Info keyInfo = Info.builder().type(randomMap.key()).attr("map", impl).build();
                Object item = itemGenerator.generate(itemInfo);
                Object key = keyGenerator.generate(keyInfo);
                impl.put(key, item);
            }
            return impl;
        } catch (Exception exc) { 
            exc.printStackTrace(System.err);
        }
        return null;
    }

}
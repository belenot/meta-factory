package com.belenot.util.pojo.generator.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

import com.belenot.util.pojo.Info;
import com.belenot.util.pojo.annotation.Generated;
import com.belenot.util.pojo.generator.Generator;

public class RandomMapGenerator implements Generator {

    @Generated(RandomMapGenerator.class)
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RandomMap {
        int maxSize() default 10;
        int minSize() default 1;
        Class<? extends Generator> itemGenerator();
        Class<? extends Generator> keyGenerator();
        Class<? extends Map> impl() default HashMap.class;
        Class<?> item() default Object.class;
        Class<?> key() default Object.class;
    }

    @Override
    public Object generate(Info info) {
        try {
            if (!(info.getAnnotation() instanceof RandomMap)) return null;
            RandomMap randomMap = (RandomMap)info.getAnnotation();
            int size = (int)(Math.random() *(randomMap.maxSize() - randomMap.minSize())+ randomMap.minSize());
            size = size < 0 ? 10 : size;
            Generator itemGenerator = randomMap.itemGenerator().getConstructor(new Class<?>[0]).newInstance(new Object[0]);
            Generator keyGenerator = randomMap.keyGenerator().getConstructor(new Class<?>[0]).newInstance(new Object[0]);
            Map impl = randomMap.impl().getConstructor(new Class<?>[0]).newInstance(new Object[0]);
            for (int i = 0; i < size; i++) {
                Info itemInfo = new Info(randomMap.item(), Map.of("map", impl));
                Info keyInfo = new Info(randomMap.key(), Map.of("map", impl));
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
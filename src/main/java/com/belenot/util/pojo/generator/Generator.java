package com.belenot.util.pojo.generator;

import com.belenot.util.pojo.Info;
import com.belenot.util.pojo.Place;

@FunctionalInterface
public interface Generator {
    Object generate(Info info);
}
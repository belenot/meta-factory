package com.belenot.util.pojo.informator;

import java.lang.annotation.Annotation;

import com.belenot.util.pojo.Info;

public interface Informator {
    Info informate(Annotation annotation);
}
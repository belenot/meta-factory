package com.belenot.util.pojo;

import java.lang.reflect.AccessibleObject;

public class Place {
    private Class<?> clazz;
    private Object object;
    private AccessibleObject accessibleObject;

    public Place() { }
    public Place(Class<?> clazz, Object object, AccessibleObject accessibleObject) {
        this.clazz = clazz;
        this.object = object;
        this.accessibleObject = accessibleObject;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public AccessibleObject getAccessibleObject() {
        return accessibleObject;
    }

    public void setAccessibleObject(AccessibleObject accessibleObject) {
        this.accessibleObject = accessibleObject;
    }
}
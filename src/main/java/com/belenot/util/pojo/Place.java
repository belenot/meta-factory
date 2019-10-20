package com.belenot.util.pojo;

import java.lang.reflect.AccessibleObject;

public class Place {
    private Class<?> clazz;
    private Object object;
    private AccessibleObject accessibleObject;

    private Place() { }
    public Class<?> getClazz() { return clazz; }
    public Object getObject() { return object; }
    public AccessibleObject getAccessibleObject() { return accessibleObject; }

    public static Place of(Object object, AccessibleObject accessibleObject) {
        Place place = new Place();
        place.clazz = object.getClass();
        place.object = object;
        place.accessibleObject = accessibleObject;
        return place;
    }

    public static Place of(Class<?> clazz, AccessibleObject accessibleObject) {
        Place place = new Place();
        place.clazz = clazz;
        place.accessibleObject = accessibleObject;
        return place;
    }

    public static Place of(Place copy) {
        Place place = new Place();
        place.clazz = copy.getClazz();
        place.object = copy.getObject();
        place.accessibleObject = copy.getAccessibleObject();
        return place;
    }
}
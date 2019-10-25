package com.belenot.util.tests;

import com.belenot.util.annotation.Set;

public class WithDefaultNullInteger {
    @Set
    private Integer value = 2;
    @Set
    private int primitive = 3;
    public Integer getValue(){ return value;}
    public int getPrimitive() { return primitive; }
}
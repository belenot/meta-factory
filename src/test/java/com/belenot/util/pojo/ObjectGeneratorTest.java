package com.belenot.util.pojo;

import static com.belenot.util.pojo.generator.ObjectGenerator.gen;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.belenot.util.pojo.annotation.Generated;
import com.belenot.util.pojo.generator.Generator;
import com.belenot.util.pojo.generator.support.RandomNumberGenerator.RandomNumber;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ObjectGeneratorTest {
    @Test
    public void genObjectNotNull() {
        SimplePojo pojo = assertDoesNotThrow(() -> gen(SimplePojo.class));
        assertNotNull(pojo);
    }

    @Test
    public void genObjectShouldGenIntValue() {
        PojoWithGenInt pojo = assertDoesNotThrow(() -> gen(PojoWithGenInt.class));
        assertNotNull(pojo);
        assertEquals(2, pojo.getValue());
        assertEquals(2, pojo.getObjectValue());
    }

    @Test
    public void genObjectShouldRandomInt() {
        PojoWithRandomInt pojo = assertDoesNotThrow(() -> gen(PojoWithRandomInt.class));
        assertNotNull(pojo);
        assertTrue(pojo.getValue() >= 5 && pojo.getValue() <= 10);
    }
}

class SimplePojo {
    private int value = 0;
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}

class PojoWithGenInt {
    @Generated(IntGenerator.class)
    private int value = 0;
    @Generated(IntGenerator.class)
    private Integer objectValue = 0;
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    static class IntGenerator implements Generator {

        @Override
        public Object generate(Info info) {
            return 2;
        }
        
    }

    public Integer getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(Integer objectValue) {
        this.objectValue = objectValue;
    }
}

class PojoWithRandomInt {
    @RandomNumber(max = 10, min = 5)
    private int value = 0;
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}
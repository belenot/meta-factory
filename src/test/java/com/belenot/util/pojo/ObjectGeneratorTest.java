package com.belenot.util.pojo;

import static com.belenot.util.pojo.annotation.ObjectGenerator.gen;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.belenot.util.pojo.annotation.Generated;
import com.belenot.util.pojo.generator.Generator;

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
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    static class IntGenerator implements Generator {

        @Override
        public Object generate(Place place, Info info) {
            return 2;
        }
        
    }
}


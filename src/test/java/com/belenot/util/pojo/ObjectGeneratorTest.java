package com.belenot.util.pojo;

import static com.belenot.util.pojo.generator.ObjectGenerator.gen;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.belenot.util.pojo.annotation.Factoried;
import com.belenot.util.pojo.generator.AbstractFactory;
import com.belenot.util.pojo.generator.support.RandomCollectionFactory.RandomCollection;
import com.belenot.util.pojo.generator.support.RandomMapFactory.RandomMap;
import com.belenot.util.pojo.generator.support.RandomNumberFactory;
import com.belenot.util.pojo.generator.support.RandomNumberFactory.RandomNumber;
import com.belenot.util.pojo.generator.support.RandomStringFactory;
import com.belenot.util.pojo.generator.support.RandomStringFactory.RandomString;

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

    @Test
    public void genObjectShouldRandomString() {
        PojoWithRandomString pojo = assertDoesNotThrow(()->gen(PojoWithRandomString.class));
        assertNotNull(pojo);
        assertTrue(pojo.getValue().length() >= 10 && pojo.getValue().length() <= 20);
        assertTrue(pojo.getValue().chars().allMatch(c->c=='1'||c=='2'||c=='3'));
    }

    @Test
    public void genObjectShouldRandomIntCollection() {
        PojoWithRandomIntCollection pojo = assertDoesNotThrow(()->gen(PojoWithRandomIntCollection.class));
        assertNotNull(pojo);
        assertTrue(pojo.getValue().size() >=15 && pojo.getValue().size() <= 20);
        assertTrue(pojo.getValue().get(0).getClass().equals(Integer.class));
    }

    @Test
    public void genObjectShouldRandomMap() {
        PojoWithRandomMap pojo = assertDoesNotThrow(()->gen(PojoWithRandomMap.class));
        assertNotNull(pojo);
        assertTrue(pojo.getValue().size() >=10 && pojo.getValue().size() <= 30);
        assertTrue(pojo.getValue().entrySet().size() > 0);
    }
}

class SimplePojo {
    private int value = 0;
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}

class PojoWithGenInt {
    @Factoried(IntGenerator.class)
    private int value = 0;
    @Factoried(IntGenerator.class)
    private Integer objectValue = 0;
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
    public Integer getObjectValue() { return objectValue; }
    public void setObjectValue(Integer objectValue) { this.objectValue = objectValue; }

    static class IntGenerator implements AbstractFactory {

        @Override
        public Object generate(Info info) {
            return 2;
        }
        
    }

}

class PojoWithRandomInt {
    @RandomNumber(max = 10, min = 5)
    private int value = 0;
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}

class PojoWithRandomString {
    @RandomString(alphabet = "123", maxLength = 20, minLength = 10)
    private String value;
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}

class PojoWithRandomIntCollection {
    @RandomCollection(maxSize = 20, minSize = 15, itemGenerator = RandomNumberFactory.class, itemType = Integer.class)
    private List<Integer> value;
    public List<Integer> getValue() { return value; }
    public void setValue(List<Integer> value) { this.value = value; }
}

class PojoWithRandomMap {
    @RandomMap(impl = HashMap.class, item = Integer.class, key = String.class, minSize = 10, maxSize = 30,
                itemGenerator = RandomNumberFactory.class, keyGenerator = RandomStringFactory.class )
    private Map<String, Integer> value;
    public Map<String, Integer> getValue() { return value; }
    public void setValue(Map<String, Integer> value) { this.value = value; }
    
}
package com.belenot.util.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;

import com.belenot.util.pojo.annotation.Random;
import com.belenot.util.pojo.annotation.RandomValues.NumberValues;
import com.belenot.util.pojo.processor.FieldValueGenerator;
import com.belenot.util.pojo.support.ValueHolder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class PojoRandomGeneratorTest {
    private PojoRandomGenerator generator;

    @BeforeAll
    public void init() {
        generator = new PojoRandomGenerator();
        // generator.addProcessorFactory(new RandomAnnotationProcessorFactory());
    }


    @Test
    public void test1() {
        ExpPojoIntValues pojo = generator.generate(ExpPojoIntValues.class);
        assertNotNull(pojo);
        assertTrue(pojo.getVal() == 1 || pojo.getVal() == 2);
    }

    @Test
    public void test2() {
        ExpPojoDoubleValues pojo = generator.generate(ExpPojoDoubleValues.class);
        assertNotNull(pojo);
        assertTrue(pojo.getVal() >= 1 && pojo.getVal() <= 2);
    }

    @Test
    public void testCustomProcessor() {
        ExpPojoCustomProcessor pojo = generator.generate(ExpPojoCustomProcessor.class);
        assertNotNull(pojo);
        assertEquals(42, pojo.getVal());
    }

    public static class ExpPojo1 {
        public ExpPojo1() {
        }

        @Random
        private Integer val;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

    }

    public static class ExpPojoIntValues {

        @Random
        @NumberValues({1, 2 })
        private int val;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    public static class ExpPojoDoubleValues {

        @Random
        @NumberValues({1, 2 })
        private Double val;

        public Double getVal() {
            return val;
        }

        public void setVal(Double val) {
            this.val = val;
        }
    }

    public static class ExpPojoCustomProcessor {
        @Random(valueGenerator = CustomFieldValueGenerator.class)
        private Integer val;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    public static class CustomFieldValueGenerator implements FieldValueGenerator {
        @Override
        public ValueHolder generate(Field field) {
            if (field.getType().equals(Integer.class)) {
                return new ValueHolder(Integer.valueOf(42));
            } else if (field.getType().equals(int.class)) {
                return new ValueHolder(42);
            }
            return ValueHolder.empty();
        }

        @Override
        public boolean support(Field field) {
            if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
                return true;
            }
            return false;
        }
        
    }
}
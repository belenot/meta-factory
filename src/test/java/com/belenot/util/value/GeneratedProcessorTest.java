package com.belenot.util.value;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.belenot.util.value.tests.PojoWithIntValue;
import com.belenot.util.value.tests.PojoWithMetaAnnotation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class GeneratedProcessorTest {
    private GeneratedProcessor generatedProcessor = new GeneratedProcessor();


    @Test
    public void generateRandomInt() {
        PojoWithIntValue pojo = new PojoWithIntValue();
        generatedProcessor.process(pojo);
        
        assertNotNull(pojo);
        assertTrue(pojo.getValue() > 0);
    }

    @Test
    public void generateMetaAnnotated() {
        PojoWithMetaAnnotation pojo = new PojoWithMetaAnnotation();
        generatedProcessor.process(pojo);

        assertNotNull(pojo);
        assertTrue(pojo.getValue() >= 50 && pojo.getValue() <= 55);
    }
}
package com.belenot.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.belenot.util.tests.WithDefaultNullInteger;
import com.belenot.util.tests.WithRandomIntGenerator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ObjectFillerTest {
    private ObjectFiller filler;

    @BeforeAll
    public void setup() {
        filler = new ObjectFiller();
    }
    
    @Test
    public void withRandomInt() {
        WithDefaultNullInteger pojo = new WithDefaultNullInteger();
        filler.fill(pojo);
        assertNotNull(pojo);
        assertNull(pojo.getValue());
        assertEquals(0, pojo.getPrimitive());
    }

    @Test
    public void withRandomIntGenerator() {
        WithRandomIntGenerator pojo = new WithRandomIntGenerator();
        filler.fill(pojo);
        assertTrue(pojo.getValue() > 0 && pojo.getValue() < 100);
    }
}
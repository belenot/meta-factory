package com.belenot.util.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.belenot.util.pojo.annotation.Factoried;
import com.belenot.util.pojo.generator.AbstractFactory;
import com.belenot.util.pojo.generator.ObjectGenerator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ReadmeTest {    
    @Test
    public void test() {
        Person person = ObjectGenerator.gen(Person.class);
        assertNotNull(person);
        assertEquals("name1", person.getName());
    }
}

class Person {
    @Factoried(NameFactory.class)
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
class NameFactory implements AbstractFactory<String> {
    @Override
    public String generate(Info info) {
        // Caution! Very complex computation logic, do not try to understand
        return "name1";
    }
}
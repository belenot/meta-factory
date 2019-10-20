# MetaFACTORY
## Prelude
Such tools as Spring Framework is a great stuff, but actualy it is only on large projects.  
I think, it is because spring is both big and complex. It's trying to make by itself every thing you need to do.
On the other hand JS has very much libraries, which are little in size and are easier to learn. These libraries are used for what they were created for, and hence, can be combined pretty painless.
## Overview
I don't want use Spring for "Hello world" applications, but I want to use some IoC. I was impressing by spring's annotation-driven programming, so this library built on annotation processing on class for instantiating objects.
### Main Concepts
Within distilled view there's only three main things in this library:  
* @Factoried - annotate field and pass **AbstractFactory** implementation
* AbstractFactory - interface which responsible for generating value
* ObjectGenerator - holds statis gen(Class<?>) method. Pass class to it and get object
## How to use
Step 1  
Suppose you have a Person class, or you have to write your own:  
```java
public class Person {
    private String name;
    ...
}
```
Step 2  
You can place **@Factoried** on the name field:
```java
@Factoried(/*here may be your abstract factory*/)
private String name;
```
Step 3  
**@Factoried** requires **AbstractFactory** implementation, so for now let's see what the heck it is:
```java
@FunctionalInterface
public interface AbstractFactory<T> {
    T generate(Info info);
}
```
It is an generic functional interface with generic *generate* method.  
For now we will skip **Info**, see more in **Advanced**.  

Step 4  
Let's implement this:
```java
public class NameFactory implements AbstractFactory<String> {
    @Override
    public String generate(Info info) {
        // Caution! Very complex computation logic, do not try to understand
        return "name1";
    }
}
```
Step 5  
Ok, let's go Step 2 and pass our **NameFactory** to annotation:
```java
@Factoried(NameFactory.class)
private String name;
```
Step 6  
Call gen method
```java
Pojo pojo = ObjectGenerator.gen(Pojo.class);
System.out.println(pojo.getName()); // name1
```
PROFIT!!!  
Note: this example is implemented in the test class `com.belenot.util.pojo.ReadmeTest`(src/test/java/com/belenot/util/pojo/ReadmeTest.java)
## Drawbacks
* For now you can use @Factoried only on fields and annotation declaration.
* Yet there is no way to specify constructor or factory method.
* Yet there is no exception handling and fallbacks.
* Simply putting there is nothing difference from calling AbstractFactory.create(Object.class).
* It gives you some IoC, but you should handle type-safety by yourself.
* It is still raw version 

In future supprort for class annotation will be added
## Advanced
### Flow
Althought this library is very small, I want give you some conception about flow.  
(It copies some parts from "How to use" section)  
Start with gen method:

1. `gen(Person.class)` - **ObjectGenerator.gen** method instantiates **Person** by calling default constructor via reflection. Then it create **FactoriedAnnotationProcessor** and pass **Person** object to it `process(Object obj)` method.
2. **FactoriedAnnotationProcessor** searches for **@Factoried** on fields and fields annotations. If it finds those, it builds **Info** object. If **@Factoried** specified `informator`, then processor call `informator.informate(annotation)`, which returns **Info** object, and merges it with already created **Info** object. 
3. **FactoriedAnnotationProcessor** calls `generator.generate(info)`, recieves value, converts if needed and set it to field.
### Classes descriptions
#### @Factoried
```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.FIELD })
public @interface Factoried {
    Class<? extends AbstractFactory> value();
    Class<? extends Informator> informator() default DefaultInformator.class;
}
```
#### AbstractFactory 
```java
@FunctionalInterface
public interface AbstractFactory<T> {
    T generate(Info info);
}
```
#### Informator
```java
public interface Informator {
    Info informate(Annotation annotation);
}
```
#### Info (immutable)
```java
public class Info {
    private Place place;
    private Annotation annotation;
    private Class<?> type;
    private Map<String, Object> attributes = new HashMap<>();
    // getters and builder class
```
#### Place (immutable)
```java 
public class Place {
    private Class<?> clazz;
    private Object object;
    private AccessibleObject accessibleObject;
```
### Bonus
In `com.belenot.util.pojo.generator.support` package are example of custom generators, which is mainly required for creating random objects

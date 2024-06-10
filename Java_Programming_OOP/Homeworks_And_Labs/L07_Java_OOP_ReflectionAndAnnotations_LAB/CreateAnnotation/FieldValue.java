package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_LAB.CreateAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldValue {
    String value();
}

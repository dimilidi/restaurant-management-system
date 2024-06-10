package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_LAB.CreateAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class<MyAnnotationExample> clazz = MyAnnotationExample.class;

        Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();

        // Type Annotation

        Subject annotation = clazz.getAnnotation(Subject.class);

        String[] categories = annotation.categories();

        System.out.println(declaredAnnotations[0]);

        for (String category : categories) {
            System.out.println(category);
        }


        // Field Annotation

        MyAnnotationExample myAnnotationExample = new MyAnnotationExample();

        Field field = clazz.getDeclaredField("dbEndpoint");
        field.setAccessible(true);
        FieldValue annotationOfField = field.getAnnotation(FieldValue.class);
        field.set(myAnnotationExample, annotationOfField.value());

        System.out.println(annotationOfField.value());


    }

}

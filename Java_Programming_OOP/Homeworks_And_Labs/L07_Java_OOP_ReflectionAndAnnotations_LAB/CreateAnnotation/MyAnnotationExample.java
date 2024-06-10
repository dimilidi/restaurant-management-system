package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_LAB.CreateAnnotation;

@Subject(categories = {"first_category", "second_category"})
public class MyAnnotationExample {

    @FieldValue(value = "localhost://maria-db:8080")
    private String dbEndpoint;

}
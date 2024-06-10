package Homeworks_And_Labs.L04_Streams_Files_Directories_EXC;

import java.io.Serializable;

public class Course implements Serializable {

    private String name;
    private int students;

    public Course(String name, int students) {
        this.name = name;
        this.students = students;
    }
}
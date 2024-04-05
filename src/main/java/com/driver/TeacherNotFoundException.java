package com.driver;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(){
        super("Teacher data not present in the map");
    }
}
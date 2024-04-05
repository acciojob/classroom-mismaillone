package com.driver;

    public class StudentNotFoundException extends RuntimeException {
        public StudentNotFoundException(){
            super("Student data not found in the map");
    }
}

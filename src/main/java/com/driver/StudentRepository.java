package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student){
        if (student != null && !studentMap.containsKey(student.getName())) {
            studentMap.put(student.getName(), student);
            System.out.println("Student " + student.getName() + " saved in db");
        } else {
            System.out.println("Error: Unable to save student. Student object is null or already exists.");
        }
    }

    public void saveTeacher(Teacher teacher){
        if (teacher != null && !teacherMap.containsKey(teacher.getName())) {
            teacherMap.put(teacher.getName(), teacher);
            System.out.println("Teacher " + teacher.getName() + " saved in db");
        } else {
            System.out.println("Error: Unable to save teacher. Teacher object is null or already exists.");
        }
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            List<String> students = teacherStudentMapping.computeIfAbsent(teacher, k -> new ArrayList<>());
            students.add(student);
            System.out.println("Saved student-teacher pair");
        } else {
            System.out.println("Error: Student or teacher not found.");
        }
    }

    public Student findStudent(String student){
        // your code goes here
        return studentMap.get(student);
    }

    public Teacher findTeacher(String teacher){
        // your code goes here
        return teacherMap.get(teacher);
    }

    public List<String> findStudentsFromTeacher(String teacher){
        // your code goes here
        // find student list corresponding to a teacher
        return teacherStudentMapping.getOrDefault(teacher, new ArrayList<>());
    }

    public List<String> findAllStudents() {
        List<String> allStudents = new ArrayList<>();

        // Check if studentMap is null or empty
//        if (studentMap != null && !studentMap.isEmpty()) {
//            // Iterate over the entries of studentMap and add the keys (student IDs) to allStudents list
//            for (String studentId : studentMap.keySet()) {
//                allStudents.add(studentId);
//            }
//            System.out.println(studentMap);
//        }
//
//        return allStudents;
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacher(String teacher){
        // your code goes here
        teacherStudentMapping.remove(teacher);
        teacherMap.remove(teacher);
        System.out.println("Teacher " + teacher + " deleted from db");
    }

    public void deleteAllTeachers(){
        // your code goes here
        teacherStudentMapping.clear();
        teacherMap.clear();
        System.out.println("All teachers deleted from db");
    }
}
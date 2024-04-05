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
        }
    }

    public void saveTeacher(Teacher teacher){
        if (teacher != null && !teacherMap.containsKey(teacher.getName())) {
            teacherMap.put(teacher.getName(), teacher);
        }
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)) {
            List<String> students = teacherStudentMapping.getOrDefault(teacher, new ArrayList<>());
            students.add(student);
            teacherStudentMapping.put(teacher, students);
        }
    }

    public Student findStudent(String student){
        // your code goes here
        if(studentMap.containsKey(student))
            return studentMap.get(student);

        return null;
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

        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacher(String teacher){
        // your code goes here
        teacherStudentMapping.remove(teacher);
        teacherMap.remove(teacher);
    }

    public void deleteAllTeachers(){
        // your code goes here
        teacherStudentMapping.clear();
        teacherMap.clear();
    }
}
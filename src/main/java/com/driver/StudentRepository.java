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
            studentMap.put(student.getName(), student);
    }

    public void saveTeacher(Teacher teacher){
            teacherMap.put(teacher.getName(), teacher);

    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(!teacherStudentMapping.containsKey(teacher)) {
            teacherStudentMapping.put(teacher, new ArrayList<>());
        }
        teacherStudentMapping.get(teacher).add(student);
    }

    public Student findStudent(String student){
            return studentMap.get(student);
    }

    public Teacher findTeacher(String teacher){
        return teacherMap.get(teacher);
    }

    public List<String> findStudentsFromTeacher(String teacher){
        // your code goes here
        // find student list corresponding to a teacher
        return teacherStudentMapping.getOrDefault(teacher, new ArrayList<>());
    }

    public List<String> findAllStudents() {
        List<String> allStudents = new ArrayList<>();
        for (String st : studentMap.keySet()){
            allStudents.add(st);
        }
        return allStudents;
    }

    public void deleteTeacher(String teacher){
        // your code goes here
        teacherMap.remove(teacher);

        List<String> al = teacherStudentMapping.remove(teacher);
        for(int i=0; i<al.size(); i++) {
            String temp = al.get(i);
            studentMap.remove(temp);
        }

    }

    public void deleteAllTeachers(){
        // your code goes here
        for(String k: teacherStudentMapping.keySet()) {
            teacherMap.remove(k);
            List<String> al = teacherStudentMapping.remove(k);
            for(String p: al) {
                if(studentMap.containsKey(p)) {
                    studentMap.remove(p);
                }
            }
        }
    }
}
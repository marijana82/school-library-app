package com.marijana.library1223.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="accounts")
public class Account {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="first_name_student", length = 64)
    private String firstNameStudent;
    @Column(name="last_name_student", length = 64)
    private String lastNameStudent;
    @Column(name="dob")
    private LocalDate dob;
    @Column(name="student_class")
    private String studentClass;
    @Column(name="name_of_teacher")
    private String nameOfTeacher;

    //student photo???


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstNameStudent() {
        return firstNameStudent;
    }

    public void setFirstNameStudent(String firstNameStudent) {
        this.firstNameStudent = firstNameStudent;
    }

    public String getLastNameStudent() {
        return lastNameStudent;
    }

    public void setLastNameStudent(String lastNameStudent) {
        this.lastNameStudent = lastNameStudent;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getNameOfTeacher() {
        return nameOfTeacher;
    }

    public void setNameOfTeacher(String nameOfTeacher) {
        this.nameOfTeacher = nameOfTeacher;
    }
}

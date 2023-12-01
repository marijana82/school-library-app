package com.marijana.library1223.dtos;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class AccountDto {

    private Long id;
    private String firstNameStudent;
    private String lastNameStudent;
    private LocalDate dob;
    private String studentClass;
    private String nameOfTeacher;

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

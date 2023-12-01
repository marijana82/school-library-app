package com.marijana.library1223.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AccountDto {

    private Long id;
    @Size(min=3, max=128)
    private String firstNameStudent;
    @Size(min=3, max=128)
    private String lastNameStudent;
    @Past
    private LocalDate dob;
    @NotBlank
    private String studentClass;
    @Size(min=3, max=128)
    private String nameOfTeacher;

    //getters & setters
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

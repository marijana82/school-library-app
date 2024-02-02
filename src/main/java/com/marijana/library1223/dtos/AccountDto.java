package com.marijana.library1223.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
@Data
public class AccountDto {

    private Long id;
    @Size(min=1, max=128, message = "First name must contain at least 1 character.")
    private String firstNameStudent;
    @Size(min=1, max=128, message = "Last name must contain at least 1 character.")
    private String lastNameStudent;
    @Past(message = "Please provide a valid date of birth.")
    private LocalDate dob;
    @NotBlank(message = "Please provide student's class name.")
    private String studentClass;
    private String nameOfTeacher;

    private UserDto userDto;

}

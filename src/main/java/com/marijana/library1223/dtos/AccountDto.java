package com.marijana.library1223.dtos;

import jakarta.persistence.Column;
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
    @Size(min=1, max=128, message = "Name must contain at least 1 character.")
    private String nameOfTeacher;

    //relations - here we give the ids of the reservations which we want to use for connecting the relation
    //for api handleiding: i have to explain that the user can give the list of reservation ids
    //by writing in the json the field: "reservationIds": 1 - for example
    public Long[] reservationIds;

}

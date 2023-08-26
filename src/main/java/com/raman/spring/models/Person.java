package com.raman.spring.models;

import javax.validation.constraints.*;
import java.util.List;

public class Person {
    int id;
    @NotBlank(message = "Full name should not by empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Your full name should be this format: Name Surname")
    String fullName;
    @Min(value = 1900, message = "Year of birth should be greater than 1899")
    @Max(value = 2023, message = "Year of birth should be less than 2024")
    @Digits(integer = 4, fraction = 0, message = "Number of invalid connection (expected <4 bits>,<0 bits>)")
    int yearOfBirth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}

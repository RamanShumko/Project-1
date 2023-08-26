package com.raman.spring.models;

import javax.validation.constraints.*;

public class Book {
    int id;
    @NotBlank(message = "Book name should not by empty")
    String bookName;
    @NotBlank(message = "Author name should not by empty")
    String authorName;
    @Min(value = 1, message = "Year of production should be greater than 0")
    @Digits(integer = 4, fraction = 0, message = "Number of invalid connection (expected <4 bits>,<0 bits>)")
    int yearOfProduction;
    int person_id;

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }
}

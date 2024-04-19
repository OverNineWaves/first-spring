package org.rinzler.spring.models;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "not be empty")
    @Size(min = 2, max = 28, message = "name size between 2 and 28")
    private String name;

    @Min(value = 0, message = "grater than 0")
    private int age;

    @NotEmpty(message = "not be empty")
    @Email(message = "field for email")
    private String email;

    //Country, City, 123456(zip)
    @Pattern(regexp= "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Country, City, 123456(zip)")
    private String address;


    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this. email = email;
        this.address = address;
    }

    public Person(){}

    public int getId() {
         return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

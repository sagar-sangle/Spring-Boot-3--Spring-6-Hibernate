package com.mvc.validation.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Customer {

    @NotNull(message = "REQUIRED")
    @Size(min = 1,message = "REQUIRED")
    public String firstName="";

    public String lastName;

    public String email;

    private int  freePass;

    @Min(value = 0 ,message = "Passes must be greater than zero")
    @Max(value = 10,message = "passess cannot be greater than ten")
    public int getFreePass() {
        return freePass;
    }

    public void setFreePass(int freePass) {
        this.freePass = freePass;
    }

    public Customer() {

    }

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

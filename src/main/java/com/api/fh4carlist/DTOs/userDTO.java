package com.api.fh4carlist.DTOs;

import com.api.fh4carlist.Models.carModel;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class userDTO {
    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private List<carModel> ownedCars;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
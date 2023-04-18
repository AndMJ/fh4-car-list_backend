package com.api.fh4carlist.DTOs.user;

import jakarta.validation.constraints.NotNull;

public class userDTO {
    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    //private Set<carModel> ownedCars;
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

    /*public Set<carModel> getOwnedCars() {
        return ownedCars;
    }

    public void setOwnedCars(Set<carModel> ownedCars) {
        this.ownedCars = ownedCars;
    }*/
}

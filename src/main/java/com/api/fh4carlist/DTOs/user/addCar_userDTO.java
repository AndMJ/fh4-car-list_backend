package com.api.fh4carlist.DTOs.user;

import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public class addCar_userDTO {
    @NotNull
    public Set<UUID> car_id;

    public Set<UUID> getCar_id() {
        return car_id;
    }

    public void setCar_id(Set<UUID> car_id) {
        this.car_id = car_id;
    }
}

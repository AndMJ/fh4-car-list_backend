package com.api.fh4carlist.DTOs.user;

import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public class cars_userDTO {
    @NotNull
    public Set<UUID> cars;

    public Set<UUID> getCars() {
        return cars;
    }

    public void setCars(Set<UUID> cars) {
        this.cars = cars;
    }
}

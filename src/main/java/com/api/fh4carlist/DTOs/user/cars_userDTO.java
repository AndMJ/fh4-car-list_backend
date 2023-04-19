package com.api.fh4carlist.DTOs.user;

import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public class cars_userDTO {
    @NotNull
    private UUID car_id;

    public UUID getCar_id() {
        return car_id;
    }

    public void setCar_id(UUID car_id) {
        this.car_id = car_id;
    }
}

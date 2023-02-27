package com.api.fh4carlist.DTOs;

import jakarta.validation.constraints.NotBlank;

public class carDTO {
    @NotBlank
    private int carID_inGame;

    @NotBlank
    private String carManufacturer;

    @NotBlank
    private String carName;

    @NotBlank
    private int carYear;

    @NotBlank
    private String carType;

    public int getCarID_inGame() {
        return carID_inGame;
    }

    public void setCarID_inGame(int carID_inGame) {
        this.carID_inGame = carID_inGame;
    }

    public String getCarManufacturer() {
        return carManufacturer;
    }

    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    //private String carImage;
}

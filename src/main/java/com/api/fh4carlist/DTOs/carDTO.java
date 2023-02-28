package com.api.fh4carlist.DTOs;

import jakarta.validation.constraints.NotNull;

public class carDTO {
    @NotNull
    private int inGameID;

    @NotNull
    private String manufacturer;

    @NotNull
    private String name;

    @NotNull
    private int year;

    @NotNull
    private String type;

    @NotNull
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getInGameID() {
        return inGameID;
    }

    public void setInGameID(int inGameID) {
        this.inGameID = inGameID;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

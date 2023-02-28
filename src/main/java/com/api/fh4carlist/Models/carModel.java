package com.api.fh4carlist.Models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "TB_CAR")
public class carModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ID;

    @Column(nullable = false)
    private int inGameID;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private String type;

    @Column(nullable = true)
    private String image;

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

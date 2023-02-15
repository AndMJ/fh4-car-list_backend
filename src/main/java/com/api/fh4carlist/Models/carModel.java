package com.api.fh4carlist.Models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

public class carModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ID;

    @Column(nullable = false)
    private int carID_inGame;

    @Column(nullable = false)
    private String carManufacturer;

    @Column(nullable = false)
    private String carName;

    @Column(nullable = false)
    private int carYear;

    @Column(nullable = false)
    private String carType;

    @Column(nullable = true)
    private String carImage;
}

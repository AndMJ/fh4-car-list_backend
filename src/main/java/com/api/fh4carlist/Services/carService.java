package com.api.fh4carlist.Services;

import com.api.fh4carlist.Models.carModel;
import com.api.fh4carlist.Repositorys.carRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class carService {

    @Autowired
    public carRepository carRepo;

    @Transactional
    public carModel add(carModel car) {
        return carRepo.save(car);
    }

    public Optional<carModel> findById(UUID id){
        return carRepo.findById(id);
    };

    public void deleteById(UUID id){
        carRepo.deleteById(id);
    }
}

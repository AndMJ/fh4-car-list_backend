package com.api.fh4carlist.Services;

import com.api.fh4carlist.Models.carModel;
import com.api.fh4carlist.Repositorys.carRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class carService {

    @Autowired
    private carRepository carRepo;

    @Transactional
    public carModel add(carModel car) {
        return carRepo.save(car);
    }

    public Optional<carModel> findById(UUID id){
        return carRepo.findById(id);
    };

    @Transactional
    public void deleteById(UUID id){
        carRepo.deleteById(id);
    }
}

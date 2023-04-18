package com.api.fh4carlist.Services.car;

import com.api.fh4carlist.Models.car.carModel;
import com.api.fh4carlist.Repositorys.car.carRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class carService {

    @Autowired
    private carRepository carRepo;

    //Transactions
    @Transactional
    public carModel save(carModel car) {
        return carRepo.save(car);
    }

    @Transactional
    public void deleteById(UUID id){
        carRepo.deleteById(id);
    }

    //Find
    public Optional<carModel> findById(UUID id){
        return carRepo.findById(id);
    };

    //Exist
    public boolean existsByInGameID(String inGameID){
        return carRepo.existsByInGameID(inGameID);
    }

    //List
    public List<carModel> listAll(){
        return carRepo.findAll();
    }


}

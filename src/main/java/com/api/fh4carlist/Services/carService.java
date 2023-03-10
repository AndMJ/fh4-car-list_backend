package com.api.fh4carlist.Services;

import com.api.fh4carlist.Models.carModel;
import com.api.fh4carlist.Repositorys.carRepository;
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
    public carModel add(carModel car) {
        return carRepo.save(car);
    }

    @Transactional
    public carModel update(UUID id, carModel car){
        return null;
        //TODO: create update
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
    public boolean existsByInGameID(int inGameID){
        return carRepo.existsByInGameID(inGameID);
    }

    //List
    public List<carModel> listAll(){
        return carRepo.findAll();
    }


}

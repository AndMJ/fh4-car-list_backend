package com.api.fh4carlist.Services.user;

import com.api.fh4carlist.Models.car.carModel;
import com.api.fh4carlist.Models.user.userModel;
import com.api.fh4carlist.Repositorys.car.carRepository;
import com.api.fh4carlist.Repositorys.user.userRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class userService {
    @Autowired
    private userRepository userRepo;
    @Autowired
    private carRepository carRepo;

    @Transactional
    public userModel save(userModel user){
        return userRepo.save(user);
    }

    public List<userModel> listAll(){
        return userRepo.findAll();
    }

    @Transactional
    public userModel userAddCars(UUID user_id, Set<UUID> cars){
        Optional<userModel> user = userRepo.findById(user_id);
        Set<carModel> userOwnedCars = user.get().getOwnedCars();

        for(UUID id: cars){
            userOwnedCars.add(carRepo.findById(id).get());
        }

        user.get().setOwnedCars(userOwnedCars);
        return userRepo.save(user.get());
    }

}

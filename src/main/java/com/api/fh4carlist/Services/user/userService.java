package com.api.fh4carlist.Services.user;

import com.api.fh4carlist.Models.user.userModel;
import com.api.fh4carlist.Repositorys.car.carRepository;
import com.api.fh4carlist.Repositorys.user.userRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class userService {
    @Autowired
    private userRepository userRepo;
    @Autowired
    private carRepository carRepo;

    //save user
    @Transactional
    public userModel save(userModel user){
        return userRepo.save(user);
    }

    //find user data by id
    /*public Optional<Object> findUserDataById(UUID id) {
        Object userdata = userRepo.getUserData(id);
        userdata.
        return
    }*/

    //find user by id
    public Optional<userModel> findById(UUID id){
        return userRepo.findById(id);
    }

    //list all users
    public List<userModel> listAll(){
        return userRepo.findAll();
    }

    @Transactional
    public void deleteById(UUID id) {
        userRepo.deleteById(id);
    }
}

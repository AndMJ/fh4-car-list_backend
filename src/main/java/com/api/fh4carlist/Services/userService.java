package com.api.fh4carlist.Services;

import com.api.fh4carlist.Models.userModel;
import com.api.fh4carlist.Repositorys.userRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {
    @Autowired
    private userRepository userRepo;

    @Transactional
    public userModel save(userModel user){
        return userRepo.save(user);
    }

    public List<userModel> listAll(){
        return userRepo.findAll();
    }

}

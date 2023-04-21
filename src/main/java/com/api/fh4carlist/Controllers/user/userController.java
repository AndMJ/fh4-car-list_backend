package com.api.fh4carlist.Controllers.user;

import com.api.fh4carlist.DTOs.user.cars_userDTO;
import com.api.fh4carlist.DTOs.user.userDTO;
import com.api.fh4carlist.Models.car.carModel;
import com.api.fh4carlist.Models.user.userModel;
import com.api.fh4carlist.Services.car.carService;
import com.api.fh4carlist.Services.user.userService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class userController {
    @Autowired
    private carService carServ;
    @Autowired
    private userService userServ;

    @PostMapping("/create")
    public ResponseEntity<Object> userCreate (@RequestBody @Valid userDTO user){
        try {
            var userToSave = new userModel();
            BeanUtils.copyProperties(user, userToSave);
            return ResponseEntity.status(HttpStatus.CREATED).body(userServ.save(userToSave));
        } catch (
                BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/list")
    public ResponseEntity<Object> userlistAll (){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userServ.listAll());
        } catch (
                BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/view/{user_id}")
    public ResponseEntity<Object> userFindByID(@PathVariable(name = "user_id") UUID id){
        try {
            Optional<userModel> user = userServ.findById(id);
            if (user.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }
            return ResponseEntity.status(HttpStatus.FOUND).body(user.get());
        } catch (
                BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    //TODO: USER DATA UPDATE/EDIT

    @GetMapping("/car/list/{user_id}")
    public ResponseEntity<Object> userFindCars(@PathVariable(name = "user_id") UUID id){
        try {
            Optional<userModel> user = userServ.findById(id);
            if(user.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }
            Set<carModel> userOwnedCars = user.get().getOwnedCars();
            return ResponseEntity.status(HttpStatus.OK).body(userOwnedCars);
        } catch (
                BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/car/add/{user_id}")
    public ResponseEntity<Object> userAddCars(@PathVariable(value = "user_id") UUID user_id, @RequestBody cars_userDTO car){
        try {
            Optional<userModel> user = userServ.findById(user_id); //get the user
            if(user.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            Set<carModel> userOwnedCars = user.get().getOwnedCars(); // get user owned cars
            userOwnedCars.add(carServ.findById(car.getCar_id()).get()); // add car to owned cars list/set
            user.get().setOwnedCars(userOwnedCars);//"save" new owned car list/set

            return ResponseEntity.status(HttpStatus.OK).body(userServ.save(user.get()));// actual db user save
        } catch (BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/car/remove/{user_id}")
    public ResponseEntity userRemoveCars(@PathVariable(name = "user_id") UUID user_id, @RequestBody cars_userDTO car){
        try {
            Optional<userModel> user = userServ.findById(user_id); //get the user
            if(user.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            Set<carModel> userOwnedCars = user.get().getOwnedCars(); // get user owned cars
            if(userOwnedCars.isEmpty()){// if owned cars list/Set doesn't exist
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User has no cars to remove.");
            }

            boolean carRemovedValidation = userOwnedCars.remove(carServ.findById(car.getCar_id()).get()); // remove car to owned cars list/set, .remove() returns true if removed, false if it doesn't
            if (!carRemovedValidation){ //if requested car doesn't exist
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User already doesn't own the car.");
            }

            user.get().setOwnedCars(userOwnedCars);//"save" new owned car list/set

            return ResponseEntity.status(HttpStatus.OK).body(userServ.save(user.get()));// actual db user save
        } catch (BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<Object> userDelete(@PathVariable(name = "user_id") UUID id){
        try {
            userServ.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted.");
        } catch (BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

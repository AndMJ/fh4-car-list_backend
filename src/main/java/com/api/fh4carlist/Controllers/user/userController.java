package com.api.fh4carlist.Controllers.user;

import com.api.fh4carlist.DTOs.user.addCar_userDTO;
import com.api.fh4carlist.DTOs.user.userDTO;
import com.api.fh4carlist.Models.car.carModel;
import com.api.fh4carlist.Models.user.userModel;
import com.api.fh4carlist.Services.car.carService;
import com.api.fh4carlist.Services.user.userService;
import jakarta.validation.Valid;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.List;
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
    public ResponseEntity<Object> save (@RequestBody @Valid userDTO user){
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
    public ResponseEntity<Object> listAll (){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userServ.listAll());
        } catch (
                BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Object> userFindByID(@PathVariable(name = "id") UUID id){
        try {
            Optional<userModel> user = userServ.findByID(id);
            if (user.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            return ResponseEntity.status(HttpStatus.FOUND).body(user.get());
        } catch (
                BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/view/cars/{id}")
    public ResponseEntity<Object> userFindCars(@PathVariable(name = "id") UUID id){
        try {
            Optional<userModel> user = userServ.findByID(id);
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

    @PostMapping("/add/cars/{id}")
    public ResponseEntity<Object> userAddCars(@PathVariable(value = "id") UUID user_id, @RequestBody addCar_userDTO cars){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userServ.userAddCars(user_id, cars.getCar_id()));
        } catch (BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


}

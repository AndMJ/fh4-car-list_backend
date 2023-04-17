package com.api.fh4carlist.Controllers;

import com.api.fh4carlist.DTOs.userAddCarDTO;
import com.api.fh4carlist.DTOs.userDTO;
import com.api.fh4carlist.Models.userModel;
import com.api.fh4carlist.Services.carService;
import com.api.fh4carlist.Services.userService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class userController {
    @Autowired
    private carService carServ;
    @Autowired
    private userService userServ;

    //TODO: how can i send request from postman, to say, this user now owns this, this and this car?
    //  how do i send the request? a list of UUIDs or the full car info?
    @PostMapping("/create")
    public ResponseEntity<userModel> save (@RequestBody @Valid userDTO user){
        var userToSave = new userModel();
        BeanUtils.copyProperties(user, userToSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(userServ.save(userToSave));
    }

    @GetMapping("/list")
    public ResponseEntity<List<userModel>> listAll (){
        return ResponseEntity.status(HttpStatus.OK).body(userServ.listAll());
    }

    @PostMapping("/{id}")
    public ResponseEntity<userModel> userAddCars(@PathVariable(value = "id") UUID user_id, @RequestBody userAddCarDTO cars){
        return ResponseEntity.status(HttpStatus.OK).body(userServ.userAddCars(user_id, cars.getCar_id()));
    }
}

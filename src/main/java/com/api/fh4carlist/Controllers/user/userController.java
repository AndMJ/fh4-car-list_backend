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
    public ResponseEntity<userModel> save (@RequestBody @Valid userDTO user){
        var userToSave = new userModel();
        BeanUtils.copyProperties(user, userToSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(userServ.save(userToSave));
    }

    @GetMapping("/list")
    public ResponseEntity<List<userModel>> listAll (){
        return ResponseEntity.status(HttpStatus.OK).body(userServ.listAll());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Object> userFindByID(@PathVariable(name = "id") UUID id){
        Optional<userModel> user = userServ.findByID(id);
        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(user.get());
    }

    @GetMapping("/view/cars/{id}")
    public ResponseEntity<Object> userFindCars(@PathVariable(name = "id") UUID id){
        Optional<userModel> user = userServ.findByID(id);
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        Set<carModel> userOwnedCars = user.get().getOwnedCars();
        return ResponseEntity.status(HttpStatus.OK).body(userOwnedCars);
    }

    @PostMapping("/add/cars/{id}")
    public ResponseEntity<userModel> userAddCars(@PathVariable(value = "id") UUID user_id, @RequestBody addCar_userDTO cars){
        return ResponseEntity.status(HttpStatus.OK).body(userServ.userAddCars(user_id, cars.getCar_id()));
    }


}

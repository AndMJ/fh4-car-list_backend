package com.api.fh4carlist.Controllers;

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
}

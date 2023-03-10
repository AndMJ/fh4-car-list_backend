package com.api.fh4carlist.Controllers;

import com.api.fh4carlist.DTOs.carDTO;
import com.api.fh4carlist.Models.carModel;
import com.api.fh4carlist.Services.carService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/car")
public class carController {

    @Autowired
    private carService carServ;

    @PostMapping("/create")
    public ResponseEntity<Object> create (@RequestBody @Valid carDTO dto){
        //ve se ja existe o carro, por inGameId
        if (carServ.existsByInGameID(dto.getInGameID())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ja existe na DB.");
        }

        //cria novo model de car
        var newCar = new carModel();
        //copia o que vem do request body, depois de validado pelo dto, para o model
        BeanUtils.copyProperties(dto, newCar);

        //newCar.setImage("");

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(carServ.add(newCar));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update (@PathVariable(value = "id") UUID id, @RequestBody @Valid carDTO dto){
        Optional<carModel> carToUpdate = carServ.findById(id);
        if(carToUpdate.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }

        var carUpdate = new carModel();
        BeanUtils.copyProperties(dto, carUpdate);

        return ResponseEntity.status(HttpStatus.OK).body(carServ.update(id, carUpdate));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete (@PathVariable(value = "id") UUID id){
        Optional<carModel> carToDelete = carServ.findById(id);

        if (carToDelete.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }

        carServ.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");

    }

    @GetMapping("/list")
    public ResponseEntity<Object> listAllCars () {
        return ResponseEntity.status(HttpStatus.OK).body(carServ.listAll());
    }
}

package com.api.fh4carlist.Controllers;

import com.api.fh4carlist.DTOs.carDTO;
import com.api.fh4carlist.Models.carModel;
import com.api.fh4carlist.Services.carService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/fh4")
public class carController {

    @Autowired
    public carService carServ;

    @PostMapping("/create")
    public ResponseEntity<Object> create (@RequestBody @Valid carDTO dto){

        //cria novo model de car
        var newCar = new carModel();

        //copia o que vem do request body, depois de validado pelo dto, para o model
        BeanUtils.copyProperties(dto, newCar);

        return ResponseEntity.status(HttpStatus.CREATED).body(carServ.add(newCar));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete (@PathVariable(value = "id") UUID id){
        Optional<carModel> carToDelete = carServ.findById(id);

        if (!carToDelete.isEmpty()){
            carServ.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("deleted");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("not found");
        }

    }
}

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
@RequestMapping("/api/car")
public class carController {

    @Autowired
    private carService carServ;

    @PostMapping("/create")
    public ResponseEntity<Object> create (@RequestBody @Valid carDTO dto){

        //cria novo model de car
        var newCar = new carModel();
        //copia o que vem do request body, depois de validado pelo dto, para o model
        BeanUtils.copyProperties(dto, newCar);

        //newCar.setImage("");

        return ResponseEntity.status(HttpStatus.CREATED).body(carServ.add(newCar));
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
}

package com.api.fh4carlist.Controllers.car;

import com.api.fh4carlist.DTOs.car.carDTO;
import com.api.fh4carlist.DTOs.car.patch_CarDTO;
import com.api.fh4carlist.Models.car.carModel;
import com.api.fh4carlist.Services.car.carService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/car")
public class carController {

    @Autowired
    private carService carServ;

    @PostMapping("/bulk")
    public ResponseEntity<Object> bulk_add (@RequestBody @Valid List<carDTO> list){

        try {
            for (carDTO car : list) {
                //ve se ja existe o carro, por inGameId
                if (carServ.existsByInGameID(car.getInGameID())) {
                    //return ResponseEntity.status(HttpStatus.CONFLICT).body("Ja existe na DB.");
                    System.out.printf("Ja existe na DB.");
                    continue;
                }

                //cria novo model de car
                var newCar = new carModel();
                //copia o que vem do request body, depois de validado pelo dto, para o model
                BeanUtils.copyProperties(car, newCar);

                carServ.save(newCar);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("All created with success.");
        } catch (BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create (@RequestBody @Valid carDTO dto) throws Exception {
        try{
            //ve se ja existe o carro, por inGameId
            if (carServ.existsByInGameID(dto.getInGameID())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ja existe na DB.");
            }

            //cria novo model de car
            var newCar = new carModel();
            //copia o que vem do request body, depois de validado pelo dto, para o model
            BeanUtils.copyProperties(dto, newCar);

            return ResponseEntity.status(HttpStatus.CREATED).body(carServ.save(newCar));
        } catch (BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update (@PathVariable(value = "id") UUID id, @RequestBody @Valid carDTO dto){
        try {
            Optional<carModel> carToUpdate = carServ.findById(id);
            if(carToUpdate.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
            }

            var carUpdate = carToUpdate.get();

            carUpdate.setName(dto.getName());
            carUpdate.setManufacturer(dto.getManufacturer());
            carUpdate.setYear(dto.getYear());
            carUpdate.setType(dto.getType());
            carUpdate.setImage(dto.getImage());
            carUpdate.setInGameID(dto.getInGameID());

            return ResponseEntity.status(HttpStatus.OK).body(carServ.save(carUpdate));
        } catch (BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> patchUpdate (@PathVariable(value = "id") UUID id, @RequestBody patch_CarDTO dto){
        try {
            Optional<carModel> carToUpdate = carServ.findById(id);
            if(carToUpdate.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
            }

            var carUpdate = carToUpdate.get();
            if(dto.getName() != null){
                carUpdate.setName(dto.getName());
            }
            if(dto.getManufacturer() != null){
                carUpdate.setManufacturer(dto.getManufacturer());
            }
            if(dto.getYear() != null){
                carUpdate.setYear(dto.getYear());
            }
            if(dto.getType() != null){
                carUpdate.setType(dto.getType());
            }
            if(dto.getImage() != null){
                carUpdate.setImage(dto.getImage());
            }
            if(dto.getInGameID() != null){
                carUpdate.setInGameID(dto.getInGameID());
            }

            return ResponseEntity.status(HttpStatus.OK).body(carServ.save(carUpdate));
        } catch (BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete (@PathVariable(value = "id") UUID id){
        try {
            Optional<carModel> carToDelete = carServ.findById(id);

            if (carToDelete.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
            }

            carServ.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("deleted");
        } catch (BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Object> findCar (@PathVariable(value = "id") UUID id) {
        try {
            Optional<carModel> car = carServ.findById(id);
                if (car.isEmpty()){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
                }
            return ResponseEntity.status(HttpStatus.OK).body(car.get());
        } catch (BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/list")
    public ResponseEntity<Object> listAllCars () {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carServ.listAll());
        } catch (BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}

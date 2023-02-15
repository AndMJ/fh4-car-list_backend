package com.api.fh4carlist.Controllers;

import com.api.fh4carlist.Services.carService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/fh4")
public class carController {

    @Autowired
    private carService carServ;

    @PostMapping("/create")
    public RequestEntity<Object> create (){

        return null;
    }
}

package com.mike.integration.controllers;

import com.mike.integration.models.Temperature;
import com.mike.integration.services.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mike.integration.utils.Decrypt.getDecryptedStr;

@RestController
@RequestMapping("/api/v1.0/temp")
public class TemperatureController {

    private TemperatureService temperatureService;
    private int id = 0;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping
    public ResponseEntity<List<Temperature>> getAllTemp() {
        List<Temperature> temps = temperatureService.getAllTemp();
        temps.stream().forEach(System.out::println);
        if(!temps.isEmpty()) {
            return new ResponseEntity<>(temps, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Temperature> addTemp(@RequestBody String temp) {
        if (temperatureService.addTemp(temp)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        /*id++;
        System.out.println("Request id = " + id + "; temp value = " + getDecryptedStr(temp));
        return new ResponseEntity<>(HttpStatus.CREATED);*/
    }
}

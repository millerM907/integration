package com.mike.integration.controllers;

import com.mike.integration.models.Humidity;
import com.mike.integration.services.HumidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mike.integration.utils.Decrypt.getDecryptedStr;

@RestController
@RequestMapping("/api/v1.0/humidity")
public class HumidityController {

    private HumidityService humidityService;
    private int id;

    @Autowired
    public HumidityController(HumidityService humidityService) {
        this.humidityService = humidityService;
    }

    @GetMapping
    public ResponseEntity<List<Humidity>> getAllHumidity() {
        List<Humidity> humidity = humidityService.getAllHumidity();
        humidity.stream().forEach(System.out::println);
        if(!humidity.isEmpty()) {
            return new ResponseEntity<>(humidity, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //принять данные, отправленные постом и создать запись в базе
    @PostMapping
    public ResponseEntity<Humidity> addHumidity(@RequestBody String humidity) {
        if (humidityService.addHumidity(humidity)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        /*id++;
        System.out.println("Request id = " + id + "; humidity value = " + getDecryptedStr(humidity));
        return new ResponseEntity<>(HttpStatus.CREATED);*/
    }
}

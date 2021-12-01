package com.mike.integration.services;

import com.mike.integration.models.Humidity;
import com.mike.integration.models.Temperature;
import com.mike.integration.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.mike.integration.utils.Decrypt.getDecryptedStr;

@Service
public class TemperatureService {

    private final TemperatureRepository temperatureRepository;

    @Autowired
    public TemperatureService(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    public boolean addTemp(String temp){
        if(temp != null) {
            temp = getDecryptedStr(temp);
            temperatureRepository.save(new Temperature(LocalDateTime.now(), Integer.valueOf(temp)));
            return true;
        }
        return false;
    }

    public Optional<Temperature> getTempById(Integer id){
        return temperatureRepository.findById(id);
    }

    public List<Temperature> getAllTemp(){
        return temperatureRepository.findAll();
    }
}

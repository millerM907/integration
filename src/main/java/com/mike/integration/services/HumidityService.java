package com.mike.integration.services;

import com.mike.integration.models.Humidity;
import com.mike.integration.repository.HumidityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.mike.integration.utils.Decrypt.getDecryptedStr;

@Service
public class HumidityService {

    private final HumidityRepository humidityRepository;

    @Autowired
    public HumidityService(HumidityRepository humidityRepository) {
        this.humidityRepository = humidityRepository;
    }

    public boolean addHumidity(String humidity){
        if(humidity != null) {
            humidity = getDecryptedStr(humidity);
            humidityRepository.save(new Humidity(LocalDateTime.now(), Integer.valueOf(humidity)));
            return true;
        }
        return false;
    }

    public Optional<Humidity> getHumidityById(Integer id){
        return humidityRepository.findById(id);
    }

    public List<Humidity> getAllHumidity(){
        return humidityRepository.findAll();
    }
}

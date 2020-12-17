package com.telegram.bot.service;

import com.telegram.bot.entity.City;
import com.telegram.bot.exception.DataNotFoundException;
import com.telegram.bot.exception.DeleteException;
import com.telegram.bot.exception.SaveException;
import com.telegram.bot.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;


    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Optional<City> findById(Integer id) {
        Optional<City> city = cityRepository.findById(id);
        if (!city.isPresent()) {
            throw new DataNotFoundException("City with ID " + id + " doesn't exist");
        }
        return city;
    }

    @Override
    public String findByName(String name) {
        return cityRepository.findByName(name).get().getDescription();
    }

    @Override
    public City update(City city) {
        if (cityRepository.exists(Example.of(city))) {
            throw new SaveException("City " + city.getName() + " is not found");
        }
        return cityRepository.save(city);
    }

    @Override
    public City create(City city) {
        return cityRepository.save(city);
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            throw new DeleteException("City with ID " + id + " is not found");
        }
        cityRepository.deleteById(id);
    }
}

package com.telegram.bot.service;

import com.telegram.bot.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    Optional<City> findById(Integer id);

    String findByName(String name);

    City update(City city);

    City create(City city);

    List<City> getAll();

    void delete(Integer id);
}

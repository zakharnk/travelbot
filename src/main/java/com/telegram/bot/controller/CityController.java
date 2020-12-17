package com.telegram.bot.controller;

import com.telegram.bot.TravelBot;
import com.telegram.bot.entity.City;
import com.telegram.bot.repository.CityRepository;
import com.telegram.bot.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.util.List;
import java.util.function.IntToDoubleFunction;

@RestController

public class CityController {

    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<City>> getAll() {

         List<City> cities =
                cityService.getAll();
        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cities, HttpStatus.OK);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable(value = "name") String name) {
        final String cityDescription = cityService.findByName(name);
        if (cityDescription == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cityDescription, HttpStatus.OK);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<City> getById(@PathVariable(value = "id") Integer id) {
        final City city = cityService.findById(id).get();
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(city, HttpStatus.OK);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@Validated @PathVariable("id") Integer id, @RequestBody City city) {
        if (id.equals(city.getCity_id())) {
            cityService.update(city);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity create(@Validated @RequestBody City city) {
        City c = null;
        if (c == null) {
            cityService.create(city);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        try {
            cityService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

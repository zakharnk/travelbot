package com.telegram.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.telegram.bot.entity.City;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findByName(String name);

}

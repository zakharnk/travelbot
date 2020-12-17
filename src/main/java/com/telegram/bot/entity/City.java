package com.telegram.bot.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "city", schema = "public", catalog = "bot")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", nullable = false)
    private Integer city_id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false, unique = true)
    private String description;

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(city_id, city.city_id) && Objects.equals(name, city.name) && Objects.equals(description, city.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city_id, name, description);
    }

    @Override
    public String toString() {
        return "City{" +
                "city_id=" + city_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

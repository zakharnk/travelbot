package com.telegram.bot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telegram.bot.entity.City;
import com.telegram.bot.service.CityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.mockito.ArgumentMatchers;
import org.springframework.http.MediaType;


import java.util.ArrayList;
import java.util.List;

@WebMvcTest
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetAll() throws Exception {
        List<City> cities = new ArrayList<>();
        City city = new City();
        city.setCity_id(14);
        city.setName("moscow");
        city.setDescription("red square");
        cities.add(city);
        Mockito.when(cityService.getAll()).thenReturn(cities);
        mockMvc.perform(get("/all")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("moscow")));
    }

    @Test
    public void testCreate() throws Exception {
        City city = new City();
        city.setCity_id(18);
        city.setName("Paris");
        city.setDescription("The Eiffel Tower");
        Mockito.when(cityService.create(ArgumentMatchers.any())).thenReturn(city);
        String json = mapper.writeValueAsString(city);
        mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());


    }

}

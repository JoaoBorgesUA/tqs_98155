package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class Controller_MockServiceTest {

    @Autowired
    private MockMvc mvc; // entry point to the web framework

    // inject required beans as "mockeable" objects
    // note that @AutoWire would result in NoSuchBeanDefinitionException
    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenPostCar_thenCreateCar() throws Exception {
        Car c = new Car("500", "Peugeot");
        c.setId((long) 100000);

        when(service.save(Mockito.any())).thenReturn(c);

        mvc.perform(
                post("/api/create").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(c)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("100000")));

        verify(service, times(1)).save(Mockito.any());

    }

    @Test
    void givenManyEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {
        Car p = new Car("500", "Peugeot");
        Car m = new Car("CLA", "Mercedes");
        Car a = new Car("TT", "Audi");

        List<Car> allCars = Arrays.asList(p, m, a);

        when(service.getAllCars()).thenReturn((ArrayList<Car>) allCars);

        mvc.perform(
                get("/api/employees").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(p.getId())))
                .andExpect(jsonPath("$[1].id", is(m.getId())))
                .andExpect(jsonPath("$[2].id", is(a.getId())));

        verify(service, times(1)).getAllCars();
    }

}

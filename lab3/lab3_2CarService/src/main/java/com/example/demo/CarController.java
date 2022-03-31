package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class CarController {

    @Autowired
    private CarManagerService service;

    @PostMapping("/create")
    public ResponseEntity<Car> createCar(Car c) {
        HttpStatus status = HttpStatus.CREATED;
        Car s = service.save(c);
        return new ResponseEntity<>(s, status);
    }

    @GetMapping("/showall")
    public List<Car> getAllCars() {
        return service.getAllCars();
    }

    @GetMapping("/car/{id}")
    public Car getCarById(@PathVariable(value = "id") Long id) {
        return service.getCarDetails(id);
    }
}

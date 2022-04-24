package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarManagerService implements CarServiceInt {

    @Autowired
    private CarRepository rep;

    @Override
    public Car save(Car c) {
        return rep.save(c);
    }

    @Override
    public List<Car> getAllCars() {
        return rep.findAll();
    }

    @Override
    public Car getCarDetails(Long Id) {
        return rep.findByCarID(Id);
    }
}

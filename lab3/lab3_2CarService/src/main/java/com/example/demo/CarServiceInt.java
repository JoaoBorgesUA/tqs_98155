package com.example.demo;

import java.util.List;

public interface CarServiceInt {
    public Car save(Car c);

    public List<Car> getAllCars();

    public Car getCarDetails(Long l);

}

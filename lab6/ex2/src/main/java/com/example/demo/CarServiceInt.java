package com.example.demo;

import java.util.List;
import java.util.Optional;

public interface CarServiceInt {
    public Car save(Car c);

    public List<Car> getAllCars();

    public Optional<Car> getCarDetails(Long l);

}

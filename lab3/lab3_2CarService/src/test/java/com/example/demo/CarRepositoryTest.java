package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository repo;

    @Test
    void FindById_Test() {
        // arrange a new employee and insert into db
        Car c = new Car("41", "Rover");
        entityManager.persistAndFlush(c); // ensure data is persisted at this point

        // test the query method of interest
        Car found = repo.findByCarID(c.getId());
        assertThat(found).isEqualTo(c);
    }

    @Test
    void FindByWrongID_Test() {
        Car fromDb = repo.findByCarID(-10L);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfEmployees_whenFindAll_thenReturnAllCars() {
        Car c = new Car("41", "Rover");

        Car c2 = new Car("I8", "BMW");
        Car c3 = new Car("Model S", "Tesla");

        entityManager.persist(c);
        entityManager.persist(c2);
        entityManager.persist(c3);
        entityManager.flush();

        List<Car> allCars = repo.findAll();
        assertThat(allCars).hasSize(3).extracting(Car::getId).contains(c.getId(), c2.getId(),
                c3.getId());
    }

}

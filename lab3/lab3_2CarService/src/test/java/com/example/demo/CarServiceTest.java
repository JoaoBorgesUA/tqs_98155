package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    @BeforeEach
    public void setUp() {

        // these expectations provide an alternative to the use of the repository
        Car c = new Car("41", "Rover");
        c.setId(100L);

        Car c2 = new Car("I8", "BMW");
        Car c3 = new Car("Model S", "Tesla");

        List<Car> allCars = Arrays.asList(c, c2, c3);

        Mockito.when(carRepository.findById(c.getId())).thenReturn(Optional.of(c));
        Mockito.when(carRepository.findById(c2.getId())).thenReturn(Optional.of(c2));
        Mockito.when(carRepository.findById(c.getId() - 3)).thenReturn(null);
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
        Mockito.when(carRepository.findById((long) 10)).thenReturn(Optional.empty());
    }

    @Test
    void whenSearchValidID_thenCarFound() {
        Car found = carService.getCarDetails(100L).orElse(null);

        assertThat(found.getId()).isEqualTo(100L);
        verifyFindByIDIsCalledOnce();
    }

    @Test
    void whenSearchInvalidID_thenCarNotFound() {
        Optional<Car> c = carService.getCarDetails(-10L);
        assertThat(c).isEmpty();

        verifyFindByIDIsCalledOnce();
    }

    @Test
    void FindAll_Test() {
        Car c = new Car("41", "Rover");
        c.setId(100L);

        Car c2 = new Car("I8", "BMW");
        Car c3 = new Car("Model S", "Tesla");

        List<Car> allCars = Arrays.asList(c, c2, c3);
        assertThat(allCars).hasSize(3).extracting(Car::getId).contains(c.getId(), c2.getId(),
                c3.getId());
    }

    private void verifyFindByIDIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
    }

}

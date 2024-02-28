package org.example.proiect_2.service;

import lombok.RequiredArgsConstructor;
import org.example.proiect_2.dto.CarDto;
import org.example.proiect_2.models.Car;
import org.springframework.stereotype.Service;


import java.util.List;

public interface CarService {

    CarDto registerCar(Car car);
    CarDto getCarById(Integer id);

    List<CarDto> getAllCars();

    CarDto createCar(Car car);

    CarDto updateCar(Integer id, Car car);

    void deleteCar(Integer id);
}

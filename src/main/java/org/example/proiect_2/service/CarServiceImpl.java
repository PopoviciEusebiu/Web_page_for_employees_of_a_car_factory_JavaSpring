package org.example.proiect_2.service;

import lombok.RequiredArgsConstructor;
import org.example.proiect_2.dto.CarDto;
import org.example.proiect_2.dto.UserDto;
import org.example.proiect_2.mapper.CarMapper;
import org.example.proiect_2.models.Car;
import org.example.proiect_2.models.User;
import org.example.proiect_2.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    public CarDto getCarById(Integer id){
        return carMapper.carEntityToDto(carRepository.findById(id).orElse(null));
    }

    public List<CarDto> getAllCars(){
        return carMapper.carListEntityToDto(carRepository.findAllCars());
    }

    public CarDto registerCar(Car car){
        Car car1 = Car.builder()
                .name(car.getName())
                .model(car.getModel())
                .horsePower(car.getHorsePower())
                .motorPower(car.getMotorPower())
                .build();
        CarDto savedCar = this.createCar(car1);

        if (savedCar != null) {
            System.out.println("Car created successfully: " + car.getName());
        } else {
            System.out.println("Car was not created!");
        }

        return savedCar;
    }
    public CarDto createCar(Car car){
        return carMapper.carEntityToDto(carRepository.save(car));
    }

    public CarDto updateCar(Integer id, Car car) {
        Car existingCar = carRepository.findById(id).orElse(null);

        if (existingCar != null) {
            existingCar.setName(car.getName());
            existingCar.setModel(car.getModel());
            existingCar.setHorsePower(car.getHorsePower());
            existingCar.setMotorPower(car.getMotorPower());


            CarDto updatedCarDto = carMapper.carEntityToDto(carRepository.save(existingCar));

            System.out.println("Car updated successfully: " + updatedCarDto.name());
            return updatedCarDto;
        } else {
            System.out.println("Car update failed. Car not found with ID: " + id);
            return null;
        }
    }

    public  void deleteCar(Integer id){
        carRepository.deleteById(id);
    }
}

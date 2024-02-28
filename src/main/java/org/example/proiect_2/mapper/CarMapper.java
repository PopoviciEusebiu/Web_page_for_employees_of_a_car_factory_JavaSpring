package org.example.proiect_2.mapper;

import org.example.proiect_2.dto.CarDto;
import org.example.proiect_2.models.Car;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {
    public CarDto carEntityToDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .name(car.getName())
                .model(car.getModel())
                .horsePower(car.getHorsePower())
                .motorPower(car.getMotorPower())
                .build();
    }

    public List<CarDto> carListEntityToDto(List<Car> cars) {
        return cars.stream()
                .map(this::carEntityToDto)
                .collect(Collectors.toList());
    }

    public Car carDtoToEntity(CarDto carDto) {
        return Car.builder()
                .id(carDto.id())
                .name(carDto.name())
                .model(carDto.model())
                .horsePower(carDto.horsePower())
                .motorPower(carDto.motorPower())
                .build();
    }

    public List<Car> carListDtoToEntity(List<CarDto> carDtos) {
        return carDtos.stream()
                .map(this::carDtoToEntity)
                .collect(Collectors.toList());
    }
}

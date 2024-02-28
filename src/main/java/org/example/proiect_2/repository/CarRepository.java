package org.example.proiect_2.repository;

import org.example.proiect_2.models.Car;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findByName(String name);

    Car findByModel(String model);
    boolean existsByNameAndModel(String emailAddress, String Model);

    @EntityGraph(attributePaths = {"name", "model", "horsePower", "motorPower"})
    @Query("SELECT c FROM Car c")
    List<Car> findAllCars();

}

package org.example.proiect_2.controller;

import lombok.RequiredArgsConstructor;
import org.example.proiect_2.dto.CarDto;
import org.example.proiect_2.dto.UserDto;
import org.example.proiect_2.models.Car;
import org.example.proiect_2.models.User;
import org.example.proiect_2.service.CarService;
import org.example.proiect_2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class CarController {
    private final CarService carService;
    private final UserService userService;

    @GetMapping("/cars/read")
    public String getCars(Model model){
        List<CarDto> cars = carService.getAllCars();
        UserDto userDto = userService.getLoginUser();
        model.addAttribute("title", "Cars");
        model.addAttribute("cars", cars);
        model.addAttribute("user", userDto);
        return "user";
    }

    @GetMapping("/createCar")
    public String renderCreateCarForm(Model model){
        UserDto userDto = userService.getLoginUser();
        model.addAttribute("title", "Create Car");
        model.addAttribute("car", new Car());
        model.addAttribute("user", userDto);
        return "user/create";
    }
    @PostMapping("/createCar")
    public String createUser(@ModelAttribute("car") Car car, Model model){
        CarDto carDto = carService.registerCar(car);

        model.addAttribute("car", "Success");
        return "redirect:/user/cars/read";
    }

    @GetMapping("/deleteCar")
    public String renderDeleteCarForm(Model model){
        List<CarDto> cars = carService.getAllCars();
        UserDto userDto = userService.getLoginUser();
        model.addAttribute("title", "Delete Cars");
        model.addAttribute("cars", cars);
        model.addAttribute("user", userDto);
        return "user/delete";
    }

    @PostMapping("/deleteCar")
    public String deleteCars(@RequestParam int[] selectedCars) {
        if (selectedCars != null && selectedCars.length > 0) {
            for (int carId : selectedCars) {
                carService.deleteCar(carId);
            }
        }
        return "redirect:/user/cars/read";
    }

    @GetMapping("/updateCar/{id}")
    public String renderUpdateCarForm(@PathVariable("id") Integer id, Model model) {
        List<CarDto> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        CarDto carDto = carService.getCarById(id);
        UserDto userDto = userService.getLoginUser();
        model.addAttribute("title", "Update Car");
        model.addAttribute("car", carDto);
        model.addAttribute("id", id);
        model.addAttribute("user", userDto);

        return "user/update";
    }

    @PostMapping("/updateCar/{id}")
    public String updateCar(@PathVariable("id") Integer id,
                            @ModelAttribute("car") Car car,
                            @RequestParam int[] selectedCars) {
        if (selectedCars != null && selectedCars.length > 0) {
            for (int carId : selectedCars) {
                carService.updateCar(carId, car);
            }
        }
        return "redirect:/user/cars/read";
    }
}

package org.example.proiect_2.dto;

// CarDto.java
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder
public record CarDto(
         Integer id,
         String name,
         String model,
         Integer horsePower,
         Float motorPower,
         List<UserDto> users) {}
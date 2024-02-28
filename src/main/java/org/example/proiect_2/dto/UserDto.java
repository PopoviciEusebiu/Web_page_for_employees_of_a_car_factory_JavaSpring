package org.example.proiect_2.dto;

import lombok.Builder;
import java.util.List;
import java.time.LocalDate;

@Builder
public record UserDto(
        Integer id,
        String username,
        List<RoleDto> roles,
        List<CarDto> cars,
        String firstName,
        String lastName,
        String emailAddress) {}
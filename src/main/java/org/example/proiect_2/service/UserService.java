package org.example.proiect_2.service;


import org.example.proiect_2.dto.UserDto;
import org.example.proiect_2.models.RegistrationRequest;
import org.example.proiect_2.models.User;

import java.util.List;

public interface UserService {

    boolean checkEmail(String email);

    UserDto registerUser(RegistrationRequest registrationRequest);

    UserDto getLoginUser();

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    UserDto createUser(User user);

    UserDto updateUser(Integer id, RegistrationRequest registrationRequest);

    void deleteUser(Integer id);
}

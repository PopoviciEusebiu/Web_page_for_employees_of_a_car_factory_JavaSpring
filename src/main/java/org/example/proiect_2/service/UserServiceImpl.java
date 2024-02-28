package org.example.proiect_2.service;

import lombok.RequiredArgsConstructor;
import org.example.proiect_2.dto.UserDto;
import org.example.proiect_2.models.RegistrationRequest;
import org.example.proiect_2.models.User;
import org.example.proiect_2.repository.RoleRepository;
import org.example.proiect_2.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.proiect_2.mapper.RoleMapper;
import org.example.proiect_2.mapper.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    @Override
    public boolean checkEmail(String email) {
        return userRepository.existsByEmailAddress(email);
    }

    @Override
    public UserDto registerUser(RegistrationRequest registrationRequest) {
        User user = User.builder()
                .username(registrationRequest.getUsername())
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .password(registrationRequest.getPassword())
                .emailAddress(registrationRequest.getEmailAddress())
                .role((roleRepository.findByRole("USER")))
                .build();

        UserDto savedUser = this.createUser(user);

        if (savedUser != null) {
            System.out.println("User registered successfully: " + user.getUsername());
        } else {
            System.out.println("User registration failed");
        }

        return savedUser;
    }

    public UserDto getLoginUser(){
        return userMapper.userEntityToDto(userRepository.findLoginUser().orElse(null));
    }

    public UserDto getUserById(Integer id){
        return userMapper.userEntityToDto(userRepository.findById(id).orElse(null));
    }

    public List<UserDto> getAllUsers(){
        return userMapper.userListEntityToDto(userRepository.findAll());
    }

    public UserDto createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.userEntityToDto(userRepository.save(user));
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    public UserDto updateUser(Integer id, RegistrationRequest registrationRequest) {
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {
            existingUser.setUsername(registrationRequest.getUsername());
            existingUser.setFirstName(registrationRequest.getFirstName());
            existingUser.setLastName(registrationRequest.getLastName());
            existingUser.setEmailAddress(registrationRequest.getEmailAddress());

            if (registrationRequest.getPassword() != null && !registrationRequest.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            }

            UserDto updatedUserDto = userMapper.userEntityToDto(userRepository.save(existingUser));

            System.out.println("User updated successfully: " + updatedUserDto.username());
            return updatedUserDto;
        } else {
            System.out.println("User update failed. User not found with ID: " + id);
            return null;
        }
        }
}


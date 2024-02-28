package org.example.proiect_2.mapper;

import lombok.RequiredArgsConstructor;
import org.example.proiect_2.dto.UserDto;
import org.example.proiect_2.models.User;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final RoleMapper roleMapper;

    public UserDto userEntityToDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(roleMapper.roleListEntityToDto(user.getRoles()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .emailAddress(user.getEmailAddress())
                .build();
    }

    public List<UserDto> userListEntityToDto(List<User> users){
        return users.stream()
                .map(user -> userEntityToDto(user))
                .toList();
    }

    public User userDtoToEntity(UserDto userDto, String password){
        return User.builder()
                .id(userDto.id())
                .username(userDto.username())
                .password(password)
                .roles(roleMapper.roleListDtoToEntity(userDto.roles()))
                .firstName(userDto.firstName())
                .lastName(userDto.lastName())
                .emailAddress(userDto.emailAddress())
                .build();
    }

    public List<User> userListDtoToEntity(List<UserDto> userDtos, String password){
        return userDtos.stream()
                .map(userDto -> userDtoToEntity(userDto, password))
                .toList();
    }

}


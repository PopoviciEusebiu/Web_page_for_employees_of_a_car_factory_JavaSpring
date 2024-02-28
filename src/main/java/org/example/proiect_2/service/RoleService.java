package org.example.proiect_2.service;

import lombok.RequiredArgsConstructor;
import org.example.proiect_2.dto.RoleDto;
import org.example.proiect_2.mapper.RoleMapper;
import org.example.proiect_2.models.Role;
import org.example.proiect_2.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleDto getRoleById(Integer id){
        return roleMapper.roleEntityToDto(roleRepository.findById(Long.valueOf(id)).orElse(null));
    }

    public RoleDto findByRole(String role){
        return roleMapper.roleEntityToDto(roleRepository.findByRole(role));
    }

    public List<RoleDto> getAllRoles(){
        return roleMapper.roleListEntityToDto(roleRepository.findAll());
    }

    public RoleDto createRole(Role role){
        return roleMapper.roleEntityToDto(roleRepository.save(role));
    }

    public RoleDto updateRole(Role role){
        return roleMapper.roleEntityToDto(roleRepository.save(role));
    }

    public void deleteRole(Role role){
        roleRepository.delete(role);
    }

}

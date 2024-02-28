package org.example.proiect_2.repository;

import org.example.proiect_2.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByRole(String role);
}

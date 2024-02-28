package org.example.proiect_2.repository;

import org.example.proiect_2.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"roles"})
    Optional<User> findByUsername(String username);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"roles"})
    List<User> findAll();

    @Query("SELECT u FROM User u WHERE u.username = ?#{ principal.username}")
    Optional<User> findLoginUser();

    boolean existsByEmailAddress(String emailAddress);
}
package com.ejemplo.prueba.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ejemplo.prueba.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
    Optional<Role> findByName(String namne);
}

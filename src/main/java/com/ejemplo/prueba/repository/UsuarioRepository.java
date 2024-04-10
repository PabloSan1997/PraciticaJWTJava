package com.ejemplo.prueba.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ejemplo.prueba.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
    Optional<Usuario> findByUsername(String username);
}

package com.ejemplo.prueba.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ejemplo.prueba.entities.Imagen;

public interface ImagenRepository extends CrudRepository<Imagen, Long>{

    @Query("select i from Imagen i where i.id=?1 and i.usuario.username = ?2")
    Optional<Imagen> findByIdAndUsername(Long id, String username);
} 

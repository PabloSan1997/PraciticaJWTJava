package com.ejemplo.prueba.services.interfaces;

import java.util.List;

import com.ejemplo.prueba.entities.Imagen;

public interface ImagenesService {
    List<Imagen> findAll();
    Imagen createImagen(String username, Imagen imagen);
    void borrarImagen(Long id_imagen, String username);
}

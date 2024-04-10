package com.ejemplo.prueba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.prueba.entities.Imagen;
import com.ejemplo.prueba.services.interfaces.ImagenesService;

@RestController
@RequestMapping("/api/v1/images")
public class ImagenController {
    
    @Autowired
    private ImagenesService imagenesService;

    @GetMapping("")
    public ResponseEntity<?> findImages(){
        List<Imagen> imagenes = imagenesService.findAll();
        return ResponseEntity.ok().body(imagenes);
    }

    @PostMapping("")
    public ResponseEntity<?> createImage(@RequestBody Imagen imagen, @RequestAttribute String username){
        var res = imagenesService.createImagen(username, imagen);
        return ResponseEntity.status(201).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id,  @RequestAttribute String username){
        imagenesService.borrarImagen(id, username);
        return ResponseEntity.noContent().build();
    }
}

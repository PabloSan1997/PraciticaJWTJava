package com.ejemplo.prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.prueba.entities.Usuario;
import com.ejemplo.prueba.services.interfaces.UsuarioService;

@RestController
@RequestMapping("/api/v1/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<?> readUsuarios(){
        var usuarios = usuarioService.findAll();
        return ResponseEntity.ok().body(usuarios);
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody Usuario usuario){
        var usuarios = usuarioService.createUser(usuario);
        return ResponseEntity.status(201).body(usuarios);
    }

    
}

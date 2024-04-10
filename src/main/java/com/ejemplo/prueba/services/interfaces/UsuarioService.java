package com.ejemplo.prueba.services.interfaces;

import java.util.List;

import com.ejemplo.prueba.entities.Usuario;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById(Long id);
    void deleteUserById(Long id);
    Usuario createUser(Usuario usuario);
}

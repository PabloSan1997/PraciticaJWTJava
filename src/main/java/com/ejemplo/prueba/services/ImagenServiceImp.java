package com.ejemplo.prueba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplo.prueba.entities.Imagen;
import com.ejemplo.prueba.entities.Usuario;
import com.ejemplo.prueba.repository.ImagenRepository;
import com.ejemplo.prueba.repository.UsuarioRepository;
import com.ejemplo.prueba.services.interfaces.ImagenesService;

@Service
public class ImagenServiceImp implements ImagenesService{
    @Autowired
    private ImagenRepository imagenesRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public List<Imagen> findAll() {
        return (List<Imagen>) imagenesRepository.findAll();
    }

    @Override
    @Transactional
    public Imagen createImagen(String username, Imagen imagen) {
       Optional<Usuario> opUsuario = usuarioRepository.findByUsername(username);
       if(opUsuario.isEmpty()) return null; 
       Usuario usuario = opUsuario.orElseThrow();
       imagen.setUsuario(usuario);
       return imagenesRepository.save(imagen);
    }

    @Override
    @Transactional
    public void borrarImagen(Long id_imagen, String username) {
        Optional<Imagen> opImagen = imagenesRepository.findByIdAndUsername(id_imagen, username);
        opImagen.ifPresent(i -> {
            imagenesRepository.deleteById(i.getId());
        });
    }


}

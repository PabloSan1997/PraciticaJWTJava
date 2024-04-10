package com.ejemplo.prueba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplo.prueba.entities.Role;
import com.ejemplo.prueba.entities.Usuario;
import com.ejemplo.prueba.repository.RoleRepository;
import com.ejemplo.prueba.repository.UsuarioRepository;
import com.ejemplo.prueba.services.interfaces.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
       return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        Optional<Usuario> usOptional = usuarioRepository.findById(id);
        if(usOptional.isPresent()){
            return usOptional.orElseThrow();
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Usuario createUser(Usuario usuario) {
        Optional<Role> oprole = roleRepository.findByName("ROLE_USER");
        if(oprole.isEmpty()) return null;
        Role role = oprole.orElseThrow();
        usuario.addRole(role);
        String password = usuario.getPassword();
        String encoder = passwordEncoder.encode(password);
        usuario.setPassword(encoder);
        return usuarioRepository.save(usuario);
    }
    
}

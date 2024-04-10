package com.ejemplo.prueba.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplo.prueba.entities.Usuario;
import com.ejemplo.prueba.repository.UsuarioRepository;

@Service
public class UserServiceDetails implements UserDetailsService{

    @Autowired
    private UsuarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> opUser = repository.findByUsername(username);

        if(opUser.isEmpty()){
            throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema", username));
        }

        Usuario user = opUser.orElseThrow();
        List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(r -> new SimpleGrantedAuthority(r.getName()))
        .collect(Collectors.toList());

        return new  User(
            user.getUsername(),
            user.getPassword(), 
            user.getEneable(),
            true,
            true,
            true,
            authorities
        );
    }
    
}

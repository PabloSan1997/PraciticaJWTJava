package com.ejemplo.prueba.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(unique = true)
    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private Boolean eneable;

    @PrePersist
    public void prePersists(){
        eneable = true;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "usuario" )
    @JsonIgnoreProperties({"usuario","handler", "hibernateLazyInitializer"})
    @Getter @Setter
    private List<Imagen> imagenes;

    @ManyToMany
    @JsonIgnoreProperties({"usuarios","handler", "hibernateLazyInitializer"})
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "id_user"),
        inverseJoinColumns = @JoinColumn(name="id_role"),
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id_user", "id_role"})}
    )
    @Getter @Setter
    private List<Role> roles;

    public Usuario() {
        this.roles = new ArrayList<Role>();
    }


    public void addRole(Role role){
        this.roles.add(role);
    }
}

package com.ejemplo.prueba.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "imagen")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String description;

    @Column(name = "image_url")
    @Getter @Setter
    private String imageUrl;

    @Getter @Setter
    private Date createAt;

    @Getter @Setter
    private Date updateAt;

    @PrePersist
    public void prePersists(){
        createAt = new Date();
        updateAt = new Date();
    }

    @PreUpdate
    public void preUpdate(){
        updateAt = new Date();
    }

    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    @JsonIgnoreProperties({"imagenes", "roles"})
    @Getter @Setter
    private Usuario usuario;
}

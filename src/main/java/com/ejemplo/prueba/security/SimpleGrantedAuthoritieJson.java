package com.ejemplo.prueba.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleGrantedAuthoritieJson {
    
    @JsonCreator
    public SimpleGrantedAuthoritieJson(@JsonProperty("authority") String role){}

}

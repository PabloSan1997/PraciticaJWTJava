package com.ejemplo.prueba.security;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;

public class SecurityProperties {
    public static final SecretKey secrectKey = Jwts.SIG.HS256.key().build();
    public static final String prefixheader = "Bearer ";
    public static final String header_autorization = "Authorization";
    public static final String applicarionjson = "application/json";
}

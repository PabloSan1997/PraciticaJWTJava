package com.ejemplo.prueba.security.filters;

import static com.ejemplo.prueba.security.SecurityProperties.*;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.ejemplo.prueba.security.SimpleGrantedAuthoritieJson;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidateAuthFilter extends BasicAuthenticationFilter {

    public JwtValidateAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
                    String header = request.getHeader(header_autorization);
                    if (header == null || !header.startsWith(prefixheader)) {
                        chain.doFilter(request, response);
                        return;
                    }
            
                    String token = header.replace(prefixheader, ""); 
                    
            
                    try {
                        
                        Claims claims = Jwts.parser().verifyWith(secrectKey).build().parseSignedClaims(token).getPayload();
                        String username = claims.getSubject();
                        Object authoritiesClaims = claims.get("authorities");
                        Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                                new ObjectMapper()
                                        .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthoritieJson.class)
                                        .readValue(
                                                authoritiesClaims.toString().getBytes(),
                                                SimpleGrantedAuthority[].class));
            
                          
                        request.setAttribute("username", username);
                    
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                                null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        chain.doFilter(request, response);
            
            
                    } catch (JwtException e) {
                        Map<String, String> body = new HashMap<String, String>();
                        body.put("message", "Token no valido");
                        body.put("error", e.getMessage());
                        
                        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
                        response.setContentType(applicarionjson);
                        response.setStatus(400);
                    }
            
                }
    }



package com.abdo.learn.service;

import java.util.Map;
import java.util.function.Function;

import com.abdo.learn.model.dto.request.UserLoginRequest;

import io.jsonwebtoken.Claims;

public interface JwtService {
    
    public Claims extractAllClaims(String token);

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    public String generateToken(UserLoginRequest user);

    public String generateToken(Map<String, Object> extraClaims, UserLoginRequest user);

    public String extractUsername(String token);

    public boolean isTokenValid(String token);

}

package com.example.rms.Service;

import com.example.rms.Entity.Auth.AuthRequest;
import com.example.rms.Entity.Auth.AuthResponse;

public interface JwtService {
    public AuthResponse createJwtToken(AuthRequest jwtRequest) throws Exception;
}

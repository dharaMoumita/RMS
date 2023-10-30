package com.example.rms.Controller;


import com.example.rms.Entity.Auth.AuthRequest;
import com.example.rms.Entity.Auth.AuthResponse;
import com.example.rms.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public AuthResponse createJwtToken(@RequestBody AuthRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}
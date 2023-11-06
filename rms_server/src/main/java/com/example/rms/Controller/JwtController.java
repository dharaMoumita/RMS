package com.example.rms.Controller;


import com.example.rms.Entity.Auth.AuthRequest;
import com.example.rms.Entity.Auth.AuthResponse;
import com.example.rms.Service.JwtService;
import com.example.rms.Service.ServiceImpl.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public AuthResponse createJwtToken(@RequestBody AuthRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}
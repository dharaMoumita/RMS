package com.example.rms.Service;

import com.example.rms.Entity.Auth.AuthRequest;
import com.example.rms.Entity.Auth.AuthResponse;
import com.example.rms.Entity.Auth.User;
import com.example.rms.Jwtutil.Jwtutil;
import com.example.rms.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Service
public class JwtService {
    @Autowired
    private Jwtutil jwtUtil;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse createJwtToken(AuthRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getPassword();
        authenticate(userName, userPassword);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails.getUsername());
        System.out.println("----------------------------------------");
        System.out.println(userDetails);
        User user = userRepo.findByUserName(userName);
        return new AuthResponse(user, newGeneratedToken);
    }
    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}

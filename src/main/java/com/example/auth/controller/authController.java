package com.example.auth.controller;

import com.example.auth.domain.user.*;
import com.example.auth.repository.userRepository;
import com.example.auth.security.tokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private userRepository userRepo;

    @Autowired
    private tokenService tokenUtil;

    @PostMapping("/login")
    public ResponseEntity<loginResponseDTO> login(@RequestBody @Valid authDTO credentials) {
        var loginData = new UsernamePasswordAuthenticationToken(
                credentials.getEmailUsuario(), credentials.getPassword());

        var authenticated = authManager.authenticate(loginData);
        var user = (User) authenticated.getPrincipal();
        var jwt = tokenUtil.generateToken(user);

        return ResponseEntity.ok(new loginResponseDTO(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid registerDTO newUserData) {
        var existingUser = userRepo.findByLogin(newUserData.getLogin());
        if (existingUser != null) {
            return ResponseEntity.status(409).body("Login já está em uso.");
        }

        String hashedPassword = new BCryptPasswordEncoder().encode(newUserData.getPassword());
        var user = new User(newUserData.getLogin(), hashedPassword, newUserData.getRole());

        userRepo.save(user);
        return ResponseEntity.ok("Usuário registrado com sucesso.");
    }
}

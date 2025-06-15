package com.example.auth.controller;

import com.example.auth.domain.user.authDTO;
import com.example.auth.domain.user.loginResponseDTO;
import com.example.auth.domain.user.registerDTO;
import com.example.auth.domain.user.User;
import com.example.auth.security.tokenService;
import com.example.auth.repository.userRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class authController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private userRepository repository;
    @Autowired
    private tokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid authDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmailUsuario(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new loginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid registerDTO data) {
        if (this.repository.findByLogin(data.getLogin()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(data.getLogin(), encryptedPassword, data.getRole());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
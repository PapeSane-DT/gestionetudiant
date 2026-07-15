package com.isepat.gestionetudiant.controller;

import com.isepat.gestionetudiant.dto.AuthRequest;
import com.isepat.gestionetudiant.dto.AuthResponse;
import com.isepat.gestionetudiant.entity.Utilisateur;
import com.isepat.gestionetudiant.security.JwtUtil;
import com.isepat.gestionetudiant.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Utilisateur> register(@RequestBody AuthRequest request) {
        Utilisateur utilisateur = authService.inscrire(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateur);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Utilisateur utilisateur = authService.authentifier(request);
        String token = jwtUtil.genererToken(utilisateur.getEmail(), utilisateur.getRole());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
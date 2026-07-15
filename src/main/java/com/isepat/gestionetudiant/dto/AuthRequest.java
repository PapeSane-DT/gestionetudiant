package com.isepat.gestionetudiant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    private String nom;      // utilisé seulement pour /register, ignoré pour /login
    private String email;
    private String motDePasse;
}
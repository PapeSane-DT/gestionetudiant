package com.isepat.gestionetudiant.service;

import com.isepat.gestionetudiant.dto.AuthRequest;
import com.isepat.gestionetudiant.entity.Utilisateur;
import com.isepat.gestionetudiant.exception.EmailExistantException;
import com.isepat.gestionetudiant.exception.IdentifiantsInvalidesException;
import com.isepat.gestionetudiant.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public Utilisateur inscrire(AuthRequest request) {
        Optional<Utilisateur> existant = utilisateurRepository.findByEmail(request.getEmail());
        if (existant.isPresent()) {
            throw new EmailExistantException(request.getEmail());
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getNom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        utilisateur.setRole("USER");

        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur authentifier(AuthRequest request) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(IdentifiantsInvalidesException::new);

        if (!passwordEncoder.matches(request.getMotDePasse(), utilisateur.getMotDePasse())) {
            throw new IdentifiantsInvalidesException();
        }

        return utilisateur;
    }
}
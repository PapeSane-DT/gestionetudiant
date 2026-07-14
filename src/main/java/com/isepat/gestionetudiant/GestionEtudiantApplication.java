package com.isepat.gestionetudiant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entree de l'application Spring Boot.
 * API REST de gestion des etudiants - ISEP-AT.
 */
@SpringBootApplication
public class GestionEtudiantApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionEtudiantApplication.class, args);
    }

}
